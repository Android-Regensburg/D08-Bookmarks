package de.ur.mi.android.bookmarks.bookmarks.operations;

import de.ur.mi.android.bookmarks.bookmarks.BookmarkOperationListener;
import de.ur.mi.android.bookmarks.database.BookmarkDatabase;

public abstract class BookmarkOperation implements Runnable {

    final BookmarkDatabase db;
    final BookmarkOperationListener listener;

    public BookmarkOperation(BookmarkDatabase db, BookmarkOperationListener listener) {
        this.db = db;
        this.listener = listener;
    }
}