package de.ur.mi.android.bookmarks.ui;

public interface BookmarkViewHolderListener {

    void onViewClicked(int adapterPosition);
    void onRemoveButtonClickedInView(int adapterPosition);
}
