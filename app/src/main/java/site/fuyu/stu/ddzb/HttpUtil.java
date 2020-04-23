package site.fuyu.stu.ddzb;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;

    class HttpUtil {
        static void sendRequestWithOkhttp(String address, okhttp3.Callback callback)
        {
            OkHttpClient client=new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .build();

            Request request=new Request.Builder()
                    .get()
                    .url(address)
                    .build();
            client.newCall(request).enqueue(callback);
        }
    }