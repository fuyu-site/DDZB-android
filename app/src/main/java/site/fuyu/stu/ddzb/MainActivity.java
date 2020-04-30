package site.fuyu.stu.ddzb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements ChannelRvAdapter.ChannelClickListener {
    private ChannelLab lab= ChannelLab.getInstance();
    private ChannelRvAdapter rvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView channelRv = findViewById(R.id.channel_rv);
        rvAdapter= new ChannelRvAdapter(this,this);

        channelRv.setAdapter(rvAdapter);
        channelRv.setLayoutManager(new LinearLayoutManager(this));

        //得到网络上的数据后去更新界面
        initData();
    }
    //初始化数据
    private void initData(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler(){
            //获取数据
            @Override
            public void handleMessage(@NonNull Message msg) {
                //收到数据，处理
                if (msg.what == 1){
                    rvAdapter.notifyDataSetChanged();
                }
            }
        };
        lab.getData(handler);
    }

    @Override
    public void onChannelClick(int position) {
        if(ClickUtil.isFastClick()){
            Channel channel = lab.getChannel(position);
            Intent intent = new Intent(MainActivity.this,PlayerActivity.class);
            Log.d("DD1", "onChannelClick: "+channel);
            intent.putExtra("Channel",channel);
            startActivity(intent);

        }
    }
}