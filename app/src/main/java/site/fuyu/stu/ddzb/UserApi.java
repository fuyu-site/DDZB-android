package site.fuyu.stu.ddzb;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/user/login")
    Call<Integer> login(@Body User user);

    @POST("/user/register")
    Call<User> register(@Body User user);
}
