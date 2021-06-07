package de.ur.mi.android.bookmarks.bookmarks.operations;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;
import de.ur.mi.android.bookmarks.bookmarks.BookmarkOperationListener;
import de.ur.mi.android.bookmarks.database.BookmarkDatabase;

public class RemoveBookmarkOperation extends BookmarkOperation {

    private final Bookmark bookmark;

    public RemoveBookmarkOperation(BookmarkDatabase db, BookmarkOperationListener listener, Bookmark bookmark) {
        super(db, listener);
        this.bookmark = bookmark;
    }

    @Override
    public void run() {
        db.bookmarks().deleteAll(bookmark);
        listener.onFinished();
    }
}
