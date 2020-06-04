package de.ur.mi.android.bookmarks.ui;

import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.bookmarks.R;

public class BookmarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public View bookmarkView;
    private BookmarkViewHolderListener listener;

    public BookmarkViewHolder(View v) {
        super(v);
        ImageButton deleteButton = v.findViewById(R.id.bookmark_delete_button);
        deleteButton.setOnClickListener(this);
        bookmarkView = v;
        bookmarkView.setOnClickListener(this);
    }

    public void setBookmarkViewHolderListener(BookmarkViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener == null) {
            return;
        }
        if(v instanceof ImageButton) {
            listener.onRemoveButtonClickedInView(this.getAdapterPosition());
        } else {
            listener.onViewClicked(this.getAdapterPosition());
        }
    }
}