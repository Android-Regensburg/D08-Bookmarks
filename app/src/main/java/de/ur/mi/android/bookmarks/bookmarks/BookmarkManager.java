package de.ur.mi.android.bookmarks.bookmarks;

import android.app.Activity;
import android.content.Context;

import androidx.room.Room;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;

import de.ur.mi.android.bookmarks.database.BookmarkDatabase;

public class BookmarkManager {

    private static final String BOOKMARK_DATABASE = "bookmark-database";

    private BookmarkDatabase db;
    private Activity context;

    public BookmarkManager(Activity context) {
        this.context = context;
        db = Room.databaseBuilder(context, BookmarkDatabase.class, BOOKMARK_DATABASE).build();
    }

    public void addBookmark(Bookmark bookmark, OnBookmarkAddedListener listener) {
        AddBookmarkTask task = new AddBookmarkTask(db, bookmark, listener);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void removeBookmark(Bookmark bookmark, OnBookmarkRemovedListener listener) {
        RemoveBookmarkTask task = new RemoveBookmarkTask(db, bookmark, listener);
        Executors.newSingleThreadExecutor().submit(task);
    }

    public void getBookmarks(OnBookmarkListReceivedListener listener) {
        GetAllBookmarksTask task = new GetAllBookmarksTask(db, listener);
        Executors.newSingleThreadScheduledExecutor().submit(task);
    }

    public static Bookmark createBookmark(String url, String title) {
        try {
            URL bookmarkURL = new URL(url);
            Date creationDate = new Date();
            Bookmark bookmark = new Bookmark(bookmarkURL, title, creationDate);
            return bookmark;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class GetAllBookmarksTask implements Runnable {

        private final BookmarkDatabase db;
        private final OnBookmarkListReceivedListener listener;

        public GetAllBookmarksTask(BookmarkDatabase db, OnBookmarkListReceivedListener listener) {
            this.db = db;
            this.listener = listener;
        }

        @Override
        public void run() {
            final ArrayList<Bookmark> bookmarks = (ArrayList<Bookmark>) db.bookmarks().getAll();
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onBookmarkListReceived(bookmarks);
                }
            });
        }
    }

    private class AddBookmarkTask implements Runnable {

        private final BookmarkDatabase db;
        private final Bookmark bookmark;
        private final OnBookmarkAddedListener listener;

        public AddBookmarkTask(BookmarkDatabase db, Bookmark bookmark, OnBookmarkAddedListener listener) {
            this.db = db;
            this.bookmark = bookmark;
            this.listener = listener;
        }

        @Override
        public void run() {
            db.bookmarks().insertAll(bookmark);
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onBookmarkAddedListener();
                }
            });
        }
    }

    private class RemoveBookmarkTask implements Runnable {

        private final BookmarkDatabase db;
        private final Bookmark bookmark;
        private final OnBookmarkRemovedListener listener;

        public RemoveBookmarkTask(BookmarkDatabase db, Bookmark bookmark, OnBookmarkRemovedListener listener) {
            this.db = db;
            this.bookmark = bookmark;
            this.listener = listener;
        }

        @Override
        public void run() {
            db.bookmarks().deleteAll(bookmark);
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onBookmarkRemoved();
                }
            });
        }
    }



}
