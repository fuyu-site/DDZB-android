package site.fuyu.stu.ddzb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChannelApi {
    @GET("/channel")
    Call<List<Channel>> getAllChannels();

    @GET("/channel/{id}")
    Call<Channel> getChannelById(@Path("id") String id);

    @GET("/channel/t/{t}")
    Call<List<Channel>> getChannelByTitle(@Path("t") String t);

    @GET("/channel/q/{q}")
    Call<List<Channel>> getChannelByQuality(@Path("q") String q);

    @GET("/channel/{channelId}/hotcomments")
    Call<List<Comment>> getHotComments(@Path("channelId") String channelId);

    @POST("/channel/{channelId}/comment")
    Call<List<Comment>> addComment(@Path("channelId") String channelId, @Body Comment comment);

    @POST("/user/login")
    Call<Integer> login(@Body User user);
}
