package site.fuyu.stu.ddzb;


import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAdapter {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("GMT+8:00"));

    @ToJson
    String toJson(Date date) {
        return dateFormat.format(date);
    }

    @FromJson
    Date fromJson(String s) throws ParseException {
        return dateFormat.parse(s);
    }
}
