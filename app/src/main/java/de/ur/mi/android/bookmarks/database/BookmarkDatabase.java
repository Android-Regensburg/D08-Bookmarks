package de.ur.mi.android.bookmarks.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;

@Database(entities = {Bookmark.class}, version = 1)
@TypeConverters(BookmarkConverters.class)
public abstract class BookmarkDatabase extends RoomDatabase {

    public abstract BookmarkDao bookmarks();
}
