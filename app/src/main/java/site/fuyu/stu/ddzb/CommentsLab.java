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

class CommentsLab {
    private static CommentsLab INSTANCE = null;
    List<Comment> data = new ArrayList<>();

    private CommentsLab() {
//            getData();
    }

    static CommentsLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommentsLab();
        }
        return INSTANCE;
    }

    void getData(String id, Handler handler) {
        Retrofit retrofit = RetrofitClient.get();
        ChannelApi api = retrofit.create(ChannelApi.class);
        Call<List<Comment>> call = api.getHotComments(id);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                Log.d("DD1", "onResponse:  response.body().toString() " + response.body().toString());
                List<Comment> comments = response.body();
                setData(comments);
                Log.d("DD1", "onResponse: data" + data);
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
            }
        });
        Log.d("DD1", "getData: " + data);
    }

    void addComment(String id, Comment comment, Handler handler) {
        Retrofit retrofit = RetrofitClient.get();
        ChannelApi api = retrofit.create(ChannelApi.class);
        Call<List<Comment>> call = api.addComment(id, comment);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> comments = response.body();
                setData(comments);
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Message message = new Message();
                message.what = 4;
                handler.sendMessage(message);
            }
        });
    }

    private void setData(List<Comment> newData) {
        this.data = newData;
    }

    int getSize() {
        return data.size();
    }

    Comment getComments(int position) {
        return data.get(position);
    }

}
