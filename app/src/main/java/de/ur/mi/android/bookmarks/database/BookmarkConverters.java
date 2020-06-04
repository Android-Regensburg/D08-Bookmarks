package de.ur.mi.android.bookmarks.database;

import androidx.room.TypeConverter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class BookmarkConverters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static URL fromString(String value) {
        try {
            return new URL(value);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String URLToString(URL url) {
        return url.toString();
    }

}
