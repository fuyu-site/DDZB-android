package site.fuyu.stu.ddzb;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserLab {
    static final int USER_LOGIN_FAIL = -1;
    static final int USER_LOGIN_SUCCESS = 1;
    private static UserLab INSTANCE = null;
    private static String TAG = "DD1";

    private UserLab() {
    }

    public static UserLab getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new UserLab();
        }
        return INSTANCE;
    }

    public void login(String username, String password, Handler handler) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Retrofit retrofit = RetrofitClient.get();
        ChannelApi api = retrofit.create(ChannelApi.class);
        Call<Integer> cal1 = api.login(user);
        cal1.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int num = 0;
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    num = response.body();
                }

                switch (num) {
                    case 1:
                        Log.d(TAG, "登录成功!");
                        Log.d(TAG, String.valueOf(num));
                        Message msg1 = new Message();
                        msg1.what = USER_LOGIN_SUCCESS;
                        handler.sendMessage(msg1);
                        break;
                    case 0:
                        Message msg0 = new Message();
                        msg0.what = USER_LOGIN_FAIL;
                        handler.sendMessage(msg0);
                        break;
                    default:
                        Log.d(TAG, "多设备登录");
                        Log.d(TAG, String.valueOf(num));
                        Message msg3 = new Message();
                        msg3.what = USER_LOGIN_SUCCESS;
                        handler.sendMessage(msg3);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "登录失败", t);
            }
        });
    }


}
