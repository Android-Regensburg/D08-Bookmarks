package de.ur.mi.android.bookmarks.bookmarks.operations;

import java.util.ArrayList;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;
import de.ur.mi.android.bookmarks.bookmarks.BookmarkOperationResultListener;
import de.ur.mi.android.bookmarks.database.BookmarkDatabase;

public class GetAllBookmarkOperation extends BookmarkOperation {

    public GetAllBookmarkOperation(BookmarkDatabase db, BookmarkOperationResultListener listener) {
        super(db, listener);
    }

    @Override
    public void run() {
        final ArrayList<Bookmark> bookmarks = (ArrayList<Bookmark>) db.bookmarks().getAll();
        listener.onFinished();
        ((BookmarkOperationResultListener) listener).onResultsAvailable(bookmarks);
    }
}
