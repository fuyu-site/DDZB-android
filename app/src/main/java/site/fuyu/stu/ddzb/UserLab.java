package site.fuyu.stu.ddzb;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


class UserLab {
    static final int USER_LOGIN_FAIL = -1;
    static final int USER_LOGIN_SUCCESS = 1;
    static final int USER_REGISTER_SUCCESS = 1;
    static final int USER_REGISTER_FAIL = 0;
    private static UserLab INSTANCE = null;
    private static String TAG = "DD1";

    private UserLab() {
    }

    //单例模式
    static UserLab getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new UserLab();
        }
        return INSTANCE;
    }

    //User登录
    void login(String username, String password, Handler handler) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Retrofit retrofit = RetrofitClient.get();
        UserApi api = retrofit.create(UserApi.class);
        Call<Result> call = api.login(user);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NotNull Call<Result> call, @NotNull Response<Result> response) {
                Result result = response.body();
                if (result != null) {
                    switch (result.getStatus()) {
                        case 1:
                            Log.d(TAG, "登录成功!");
                            Message msg1 = new Message();
                            msg1.what = USER_LOGIN_SUCCESS;
                            handler.sendMessage(msg1);
                            break;
                        case -1:
                            Message msg_1 = new Message();
                            msg_1.what = USER_LOGIN_FAIL;
                            handler.sendMessage(msg_1);
                            break;
                        default:
                            Log.d(TAG, "多设备登录");
                            Message msg2 = new Message();
                            msg2.what = USER_LOGIN_SUCCESS;
                            handler.sendMessage(msg2);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Result> call, @NotNull Throwable t) {
                Log.e(TAG, "登录失败", t);
            }
        });
    }

    //User注册
    void register(User user, Handler handler) {
        Retrofit retrofit = RetrofitClient.get();
        UserApi api = retrofit.create(UserApi.class);
        Call<Result> call = api.register(user);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NotNull Call<Result> call, @NotNull Response<Result> response) {
                Result result = response.body();
                Log.d(TAG, "onResponse: " + result);
                if (result != null) {
                    switch (result.getStatus()) {
                        case 1:
                            Message msg1 = new Message();
                            msg1.what = USER_REGISTER_SUCCESS;
                            handler.sendMessage(msg1);
                            break;
                        case -1:
                            Message msg0 = new Message();
                            msg0.what = USER_REGISTER_FAIL;
                            handler.sendMessage(msg0);
                            break;

                    }
                } else {
                    Log.d(TAG, "onResponse:  空");
                }
                Message msg3 = new Message();
                msg3.what = USER_REGISTER_SUCCESS;
                handler.sendMessage(msg3);
            }

            @Override
            public void onFailure(@NotNull Call<Result> call, @NotNull Throwable t) {

            }
        });
    }


}
