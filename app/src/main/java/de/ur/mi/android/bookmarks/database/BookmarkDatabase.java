package de.ur.mi.android.bookmarks.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;

@Database(entities = {Bookmark.class}, version = 1)
@TypeConverters(BookmarkConverters.class)
public abstract class BookmarkDatabase extends RoomDatabase {

    private static final String BOOKMARK_DATABASE = "bookmark-database";

    public abstract BookmarkDao bookmarks();

    public static BookmarkDatabase build(Context context) {
        return Room.databaseBuilder(context, BookmarkDatabase.class, BOOKMARK_DATABASE).build();
    }
}
