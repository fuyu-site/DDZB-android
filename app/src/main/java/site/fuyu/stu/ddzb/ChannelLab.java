package site.fuyu.stu.ddzb;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class ChannelLab {
    private static ChannelLab INSTANCE = null;
    List<Channel> data = new ArrayList<>();

    private ChannelLab() {
    }

    public static ChannelLab getInstance(){
        if (INSTANCE==null){
            INSTANCE = new ChannelLab();
        }
        return INSTANCE;
    }
    /**
     * 获取网络数据
     */
//    private void get() {
//        data = new ArrayList<>();
//        String address = "https://stu.fuyu.site/channel";
//        HttpUtil.sendRequestWithOkhttp(address, new okhttp3.Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                Log.d("DD1", "onFailure: 无网络");
//            }
//            @Override
//            public void onResponse(okhttp3.Call call, Response response) throws IOException {
//                assert response.body() != null;
//                String responseData = response.body().string();
//                try {
//                    JSONArray jsonArray = new JSONArray(responseData);
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        Channel channel = new Channel();
//                        channel.setTitle(jsonObject.getString("title"));
//                        channel.setQuality(jsonObject.getString("quality"));
//                        channel.setCover(R.drawable.cover);
//                        channel.setUrl(jsonObject.getString("url"));
//                        data.add(channel);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Log.d("DD1", "onResponse: " + data);
//            }
//        });
//    }
    /**
     * 获取网络数据
     */
    public void getData(Handler handler){
        Retrofit retrofit = RetrofitClient.get();
        ChannelApi api = retrofit.create(ChannelApi.class);
        retrofit2.Call<List<Channel>> call  = api.getAllChannels();
        //enqueue自动放入代码到子线程运行
        call.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Channel>> call, retrofit2.Response<List<Channel>> response) {
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
    public void setData(List<Channel> newData){
        this.data = newData;
    }

    int getSize() {
        return data.size();
    }

    Channel getChannel(int position) {
        return data.get(position);
    }
}