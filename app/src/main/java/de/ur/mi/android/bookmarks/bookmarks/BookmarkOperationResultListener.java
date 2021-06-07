package de.ur.mi.android.bookmarks.bookmarks;

import java.util.ArrayList;

public interface BookmarkOperationResultListener extends BookmarkOperationListener {

    void onResultsAvailable(ArrayList<Bookmark> bookmarks);
}
