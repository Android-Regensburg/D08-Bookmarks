package de.ur.mi.android.bookmarks.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.ur.mi.android.bookmarks.R;
import de.ur.mi.android.bookmarks.bookmarks.Bookmark;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkViewHolder> implements BookmarkViewHolderListener{

    private ArrayList<Bookmark> bookmarks;
    private BookmarkAdapterListener listener;


    public BookmarkAdapter(BookmarkAdapterListener listener) {
        this.bookmarks = new ArrayList<>();
        this.listener = listener;
    }

    public void setBookmarks(ArrayList<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_view, parent, false);
        BookmarkViewHolder vh = new BookmarkViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarks.get(position);
        TextView title = holder.bookmarkView.findViewById(R.id.bookmark_title);
        TextView url = holder.bookmarkView.findViewById(R.id.bookmark_url);
        title.setText(bookmark.title);
        url.setText(bookmark.url.toString());
        holder.setBookmarkViewHolderListener(this);
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }


    @Override
    public void onViewClicked(int adapterPosition) {
        Bookmark bookmark = bookmarks.get(adapterPosition);
        listener.onBookmarkSelected(bookmark);
    }

    @Override
    public void onRemoveButtonClickedInView(int adapterPosition) {
        Bookmark bookmark = bookmarks.get(adapterPosition);
        listener.onBookmarkRemoveButtonClicked(bookmark);
    }
}
