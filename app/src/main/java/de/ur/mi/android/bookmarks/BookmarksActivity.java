package de.ur.mi.android.bookmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;
import de.ur.mi.android.bookmarks.bookmarks.BookmarkManager;
import de.ur.mi.android.bookmarks.bookmarks.OnBookmarkAddedListener;
import de.ur.mi.android.bookmarks.bookmarks.OnBookmarkListReceivedListener;
import de.ur.mi.android.bookmarks.bookmarks.OnBookmarkRemovedListener;
import de.ur.mi.android.bookmarks.ui.BookmarkAdapter;
import de.ur.mi.android.bookmarks.ui.BookmarkAdapterListener;

public class BookmarksActivity extends AppCompatActivity implements BookmarkAdapterListener {

    private BookmarkManager bookmarkManager;
    private BookmarkAdapter bookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        initBookmarkManager();
        initBookmarkList();
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        String intentAction = intent.getAction();
        String intentType = intent.getType();
        if(intentAction.equals(Intent.ACTION_SEND)) {
            if(intentType.equals("text/plain")) {
               addBookmarkFromIntent(intent);
            }
        }
    }

    private void initBookmarkManager() {
        bookmarkManager = new BookmarkManager(this);
    }

    private void initBookmarkList() {
        RecyclerView bookmarkListView = findViewById(R.id.bookmark_list);
        bookmarkAdapter = new BookmarkAdapter(this);
        bookmarkListView.setAdapter(bookmarkAdapter);
        updateBookmarkAdapter();
    }

    private void updateBookmarkAdapter() {
        bookmarkManager.getBookmarks(new OnBookmarkListReceivedListener() {
            @Override
            public void onBookmarkListReceived(ArrayList<Bookmark> bookmarks) {
                bookmarkAdapter.setBookmarks(bookmarks);
            }
        });
    }


    private void addBookmarkFromIntent(Intent intent) {
        String url = intent.getStringExtra(Intent.EXTRA_TEXT);
        String title = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        Bookmark newBookmark = BookmarkManager.createBookmark(url, title);
        bookmarkManager.addBookmark(newBookmark, new OnBookmarkAddedListener() {
            @Override
            public void onBookmarkAddedListener() {
                updateBookmarkAdapter();
            }
        });
    }
    private void openURLInBrowser(URL url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()));
        startActivity(browserIntent);
    }

    @Override
    public void onBookmarkSelected(Bookmark bookmark) {
        openURLInBrowser(bookmark.url);
    }

    @Override
    public void onBookmarkRemoveButtonClicked(Bookmark bookmark) {
        bookmarkManager.removeBookmark(bookmark, new OnBookmarkRemovedListener() {
            @Override
            public void onBookmarkRemoved() {
                updateBookmarkAdapter();
            }
        });
    }
}
