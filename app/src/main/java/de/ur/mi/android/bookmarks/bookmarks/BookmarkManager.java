package de.ur.mi.android.bookmarks.bookmarks;

import android.content.Context;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.Executors;

import de.ur.mi.android.bookmarks.bookmarks.operations.AddBookmarkOperation;
import de.ur.mi.android.bookmarks.bookmarks.operations.GetAllBookmarkOperation;
import de.ur.mi.android.bookmarks.bookmarks.operations.RemoveBookmarkOperation;
import de.ur.mi.android.bookmarks.database.BookmarkDatabase;

public class BookmarkManager {

    private final BookmarkDatabase db;

    public BookmarkManager(Context context) {
        db = BookmarkDatabase.build(context);
    }

    public Bookmark createLocalBookmark(String url, String title) {
        try {
            URL bookmarkURL = new URL(url);
            Date creationDate = new Date();
            return new Bookmark(bookmarkURL, title, creationDate);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storeBookmark(Bookmark bookmark, BookmarkOperationListener listener) {
        AddBookmarkOperation operation = new AddBookmarkOperation(db, listener, bookmark);
        Executors.newSingleThreadExecutor().submit(operation);
    }

    public void removeBookmark(Bookmark bookmark, BookmarkOperationListener listener) {
        RemoveBookmarkOperation operation = new RemoveBookmarkOperation(db, listener, bookmark);
        Executors.newSingleThreadExecutor().submit(operation);
    }

    public void getAllBookmarks(BookmarkOperationResultListener listener) {
        GetAllBookmarkOperation operation = new GetAllBookmarkOperation(db, listener);
        Executors.newSingleThreadScheduledExecutor().submit(operation);
    }

}
