package site.fuyu.stu.ddzb;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class LocalDateTimeAdapter {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("GMT+8:00"));

    @ToJson
    String toJson(LocalDateTime date) {
        return dtf.format(date);
    }

    @FromJson
    LocalDateTime fromJson(String s) {
        return LocalDateTime.parse(s, dtf);
    }
}

class LocalDateAdapter {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @ToJson
    String toJson(LocalDate date) {
        return dtf.format(date);
    }

    @FromJson
    LocalDate fromJson(String s) {
        return LocalDate.parse(s, dtf);
    }
}