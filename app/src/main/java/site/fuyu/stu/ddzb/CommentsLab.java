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

public class CommentsLab {
    private static CommentsLab INSTANCE = null;
    List<Comments> data = new ArrayList<>();

    CommentsLab() {
//            getData();
    }

    public static CommentsLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommentsLab();
        }
        return INSTANCE;
    }

    public void getData(String id, Handler handler) {
        Retrofit retrofit = RetrofitClient.get();
        ChannelApi api = retrofit.create(ChannelApi.class);
        Call<Channel> call = api.getChannelById(id);
        call.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {
                Log.d("DD1", "onResponse: " + response.body().toString());
                Channel channel = response.body();
                data.addAll(channel.getComments());
                Log.d("DD1", "onResponse: " + channel);

                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Channel> call, Throwable t) {

            }
        });

//        Comments comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
//        comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
//        comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
//        comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
//        comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
//        comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
//        comments = new Comments();
//        comments.setAuthor("张三");
//        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
//        data.add(comments);
        Log.d("DD1", "getData: " + data);
    }

    int getSize() {
        return data.size();
    }

    Comments getComments(int position) {
        return data.get(position);
    }
}
