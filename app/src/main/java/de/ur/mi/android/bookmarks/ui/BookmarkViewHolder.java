package de.ur.mi.android.bookmarks.ui;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.bookmarks.R;
import de.ur.mi.android.bookmarks.bookmarks.Bookmark;

public class BookmarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public View bookmarkView;
    private final BookmarkViewHolderListener listener;

    public BookmarkViewHolder(View v, BookmarkViewHolderListener listener) {
        super(v);
        bookmarkView = v;
        bookmarkView.setOnClickListener(this);
        bookmarkView.findViewById(R.id.bookmark_delete_button).setOnClickListener(this);
        this.listener = listener;
    }

    public void bindView(Bookmark bookmark) {
        TextView title = bookmarkView.findViewById(R.id.bookmark_title);
        TextView url = bookmarkView.findViewById(R.id.bookmark_url);
        title.setText(bookmark.title);
        url.setText(bookmark.url.toString());
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        if (v.getId() == R.id.bookmark_delete_button) {
            listener.onRemoveButtonClickedInView(this.getBindingAdapterPosition());
        } else {
            listener.onViewClicked(this.getBindingAdapterPosition());
        }
    }

    public interface BookmarkViewHolderListener {

        void onViewClicked(int adapterPosition);

        void onRemoveButtonClickedInView(int adapterPosition);
    }
}