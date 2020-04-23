package site.fuyu.stu.ddzb;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class PlayerActivity extends AppCompatActivity {
    PlayerView videoPlayer;
    SimpleExoPlayer player;
    MediaSource videoSource;
    long CurrentPosition;
    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        channel = (Channel) getIntent().getSerializableExtra("Channel");
        initPlayer();
        updateUI();
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
            Uri uri = Uri.parse(channel.getUrl());
            DataSource.Factory factory = new DefaultDataSourceFactory(this, "DianDian");
            videoSource = new ExtractorMediaSource.Factory(factory).createMediaSource(uri);
            // MediaSource videoSource = new HlsMediaSource.Factory(factory).createMediaSource(uri);

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
    private void play(){
        player.setPlayWhenReady(true);
    }
    private void updateUI(){
        TextView videoTitle = findViewById(R.id.videoTitle);
        TextView videoQuality = findViewById(R.id.videoQuality);
        videoTitle.setText(this.channel.getTitle());
        videoQuality.setText(this.channel.getQuality());

    }
}
