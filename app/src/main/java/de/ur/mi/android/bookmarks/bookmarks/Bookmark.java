package de.ur.mi.android.bookmarks.bookmarks;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.net.URL;
import java.util.Date;

@Entity
public class Bookmark {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;
    public final URL url;
    public final String title;
    public final Date creationDate;

    public Bookmark(URL url, String title, Date creationDate) {
        this.url = url;
        this.title = title;
        this.creationDate = creationDate;
    }

}
