package site.fuyu.stu.ddzb;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit get(){
        if (retrofit == null){
            Moshi moshi = new Moshi.Builder()
                    .add(Date.class, new Rfc3339DateJsonAdapter())
                    .add(MoshiConverterFactory.create())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://stu.fuyu.site")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }
        return retrofit;
    }
}

