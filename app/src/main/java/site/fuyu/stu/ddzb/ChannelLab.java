package site.fuyu.stu.ddzb;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

class ChannelLab {
    private static ChannelLab INSTANCE = null;
    List<Channel> data = new ArrayList<>();

    private ChannelLab() {
    }

    static ChannelLab getInstance() {
        if (INSTANCE==null){
            INSTANCE = new ChannelLab();
        }
        return INSTANCE;
    }
    /**
     * 获取网络数据
     */
    void getData(Handler handler) {
        Retrofit retrofit = RetrofitClient.get();
        ChannelApi api = retrofit.create(ChannelApi.class);
        retrofit2.Call<List<Channel>> call  = api.getAllChannels();
        //enqueue自动放入代码到子线程运行
        call.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(Call<List<Channel>> call, Response<List<Channel>> response) {
                //获取成功
                Log.d("DD1", "retrofit2 onResponse: "+response.body().toString());
                setData(response.body());
                Message message  = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(Call<List<Channel>> call, Throwable t) {

            }
        });

    }

    //修改数据方法
    private void setData(List<Channel> newData) {
        this.data = newData;
    }

    int getSize() {
        return data.size();
    }

    Channel getChannel(int position) {
        return data.get(position);
    }

}