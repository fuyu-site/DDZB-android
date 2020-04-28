package site.fuyu.stu.ddzb;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

class RetrofitClient {
    private static Retrofit retrofit = null;
    private static Object customDateAdapter = new Object() {
        final DateFormat dateFormat;

        {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        @ToJson
        synchronized String dateToJson(Date d) {
            return dateFormat.format(d);
        }

        @FromJson
        synchronized Date dateToJson(String s) throws ParseException {
            return dateFormat.parse(s);
        }
    };

    static Retrofit get() {
        if (retrofit == null){
            Moshi moshi = new Moshi.Builder()
                    .add(customDateAdapter)
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

