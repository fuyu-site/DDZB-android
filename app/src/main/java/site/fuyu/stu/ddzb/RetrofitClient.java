package site.fuyu.stu.ddzb;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

class RetrofitClient {
    private static Retrofit retrofit = null;

    static Retrofit get() {
        if (retrofit == null) {
            Moshi moshi = new Moshi.Builder()
                    .add(new LocalDateTimeAdapter())
                    .add(new LocalDateAdapter())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://stu.fuyu.site")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }
        return retrofit;
    }
}