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

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkViewHolder> implements BookmarkViewHolder.BookmarkViewHolderListener {


    private ArrayList<Bookmark> bookmarks;
    private final BookmarkAdapterListener listener;

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
        return new BookmarkViewHolder(v ,this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarks.get(position);
        holder.bindView(bookmark);
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

    public interface BookmarkAdapterListener {

        void onBookmarkSelected(Bookmark bookmark);

        void onBookmarkRemoveButtonClicked(Bookmark bookmark);

    }

}
