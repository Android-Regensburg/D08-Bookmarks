package de.ur.mi.android.bookmarks.ui;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;

public interface BookmarkAdapterListener {

    void onBookmarkSelected(Bookmark bookmark);

    void onBookmarkRemoveButtonClicked(Bookmark bookmark);

}
