package site.fuyu.stu.ddzb;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.Serializable;

public class PlayerActivity extends AppCompatActivity {
    PlayerView videoPlayer;
    SimpleExoPlayer player;
    MediaSource videoSource;
    long CurrentPosition;
    Button sendButton;
    Channel channel;
    private CommentsRvAdapter rvAdapter;
    private CommentsLab commentsLab = CommentsLab.getInstance();
//    private ChannelLab channelLab = ChannelLab.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        Serializable c = getIntent().getSerializableExtra("Channel");
        if (c instanceof Channel) {
            channel = (Channel) c;
            initPlayer();
            updateUI();
            RecyclerView commentsrv = findViewById(R.id.comments_rv);
            rvAdapter = new CommentsRvAdapter(this);
            commentsrv.setAdapter(rvAdapter);
            commentsrv.setLayoutManager(new LinearLayoutManager(this));

            initComments();

            sendComment();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DD1", "onDestroy: 清理player");
        clean();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoPlayer != null) {
            stop();
            Log.d("DD1", "onPause: 暂停player");
            CurrentPosition = player.getCurrentPosition();
            Log.d("DD1", "onPause: 记录位置" + (CurrentPosition = CurrentPosition - 1000));
            videoPlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            Log.d("DD1", "onResume: 重建player");
            //player.setPlayWhenReady(true);
            if (videoPlayer != null) {
                Log.d("DD1", "onResume: 开始播放");
                player.seekTo(CurrentPosition);
                Log.d("DD1", "onResume: 恢复进度" + CurrentPosition);
                videoPlayer.onResume();
                play();
            }
        } else {
            initPlayer();
        }
    }

    private void initPlayer() {
        if (player == null) {
            videoPlayer = findViewById(R.id.video_player);
            //创建播放器
            player = ExoPlayerFactory.newSimpleInstance(this);
            //绑定界面与播放器
            videoPlayer.setPlayer(player);
            player.setPlayWhenReady(true);
            //准备播放源
            if (channel.getUrl() != null) {
                Uri uri = Uri.parse(channel.getUrl());
                DataSource.Factory factory = new DefaultDataSourceFactory(this, "DianDian");
                videoSource = new ExtractorMediaSource.Factory(factory).createMediaSource(uri);
                // MediaSource videoSource = new HlsMediaSource.Factory(factory).createMediaSource(uri);
            }
        }
        player.prepare(videoSource);
    }

    private void clean() {
        if (player != null) {
            player.release();
            player = null;
            videoSource = null;
        }
    }

    private void stop() {
        player.setPlayWhenReady(false);
    }

    private void play() {
        player.setPlayWhenReady(true);
    }

    private void updateUI() {
        TextView videoTitle = findViewById(R.id.videoTitle);
        TextView videoQuality = findViewById(R.id.videoQuality);
        videoTitle.setText(this.channel.getTitle());
        videoQuality.setText(this.channel.getQuality());

    }

    private void initComments() {
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            //获取数据
            @Override
            public void handleMessage(@NonNull Message msg) {
                //收到数据，处理
                switch (msg.what) {
                    case 2:
                        rvAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
        commentsLab.getData(channel.getId(), handler);
    }

    private void sendComment() {
        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(v -> {
            @SuppressLint("HandlerLeak") Handler handler = new Handler() {
                //获取数据
                @Override
                public void handleMessage(@NonNull Message msg) {
                    //收到数据，处理
                    switch (msg.what) {
                        case 3:
                            Toast.makeText(PlayerActivity.this, "评论成功", Toast.LENGTH_LONG).show();
                            rvAdapter.notifyDataSetChanged();
                            EditText editText = findViewById(R.id.e_comment);
                            editText.setText("");
                            break;
                        case 4:
                            Toast.makeText(PlayerActivity.this, "评论失败", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            };
            EditText editText = findViewById(R.id.e_comment);
            Comment comment = new Comment();
            comment.setAuthor("Myapp");
            comment.setStar(100);
            if (editText.getText().toString().trim().length() != 0) {
                Log.d("DD1", "sendComment: " + editText.getText());
                comment.setContent(editText.getText().toString());
                commentsLab.addComment(channel.getId(), comment, handler);
            } else {
                Toast.makeText(PlayerActivity.this, "请输入评论", Toast.LENGTH_LONG).show();
            }
        });
    }

}
