package site.fuyu.stu.ddzb;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    //使用GET来传密码的都是NT
    @POST("/user/login")
    Call<Result> login(@Body User user);

    @POST("/user/register")
    Call<Result> register(@Body User user);
}
