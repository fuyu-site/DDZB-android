package site.fuyu.stu.ddzb;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit get(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://stu.fuyu.site")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
