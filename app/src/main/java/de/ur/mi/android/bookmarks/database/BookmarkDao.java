package de.ur.mi.android.bookmarks.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;

@Dao
public interface BookmarkDao {

    @Query("SELECT * FROM Bookmark")
    List<Bookmark> getAll();

    @Insert
    void insertAll(Bookmark... bookmarks);

    @Delete
    void deleteAll(Bookmark... bookmarks);

}
