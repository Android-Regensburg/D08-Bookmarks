package de.ur.mi.android.bookmarks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.ur.mi.android.bookmarks.bookmarks.Bookmark;
import de.ur.mi.android.bookmarks.bookmarks.BookmarkManager;
import de.ur.mi.android.bookmarks.bookmarks.BookmarkOperationListener;
import de.ur.mi.android.bookmarks.bookmarks.BookmarkOperationResultListener;
import de.ur.mi.android.bookmarks.ui.BookmarkAdapter;

public class BookmarksActivity extends AppCompatActivity implements BookmarkAdapter.BookmarkAdapterListener {

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
        Bookmark bookmark = extractBookmarkFromIntent(intent);
        if (bookmark != null) {
            addBookmark(bookmark);
        }
    }

    private Bookmark extractBookmarkFromIntent(Intent intent) {
        String intentAction = intent.getAction();
        String intentType = intent.getType();
        if (intentAction == null || intentType == null) {
            return null;
        }
        if (intentAction.equals(Intent.ACTION_SEND)) {
            if (intentType.equals("text/plain")) {
                String url = intent.getStringExtra(Intent.EXTRA_TEXT);
                String title = intent.getStringExtra(Intent.EXTRA_SUBJECT);
                return bookmarkManager.createLocalBookmark(url, title);
            }
        }
        return null;
    }

    private void initBookmarkManager() {
        bookmarkManager = new BookmarkManager(this);
    }

    private void initBookmarkList() {
        RecyclerView bookmarkListView = findViewById(R.id.bookmark_list);
        bookmarkAdapter = new BookmarkAdapter(this);
        bookmarkListView.setAdapter(bookmarkAdapter);
        updateBookmarkList();
    }

    private void addBookmark(Bookmark bookmark) {
        bookmarkManager.storeBookmark(bookmark, new BookmarkOperationListener() {
            @Override
            public void onFinished() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateBookmarkList();
                    }
                });
            }
        });
    }

    private void removeBookmark(Bookmark bookmark) {
        bookmarkManager.removeBookmark(bookmark, new BookmarkOperationListener() {
            @Override
            public void onFinished() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateBookmarkList();
                    }
                });
            }
        });
    }

    private void updateBookmarkList() {
        bookmarkManager.getAllBookmarks(new BookmarkOperationResultListener() {
            @Override
            public void onFinished() {

            }

            @Override
            public void onResultsAvailable(ArrayList<Bookmark> bookmarks) {
                final ArrayList<Bookmark> bookmarksFromManager = bookmarks;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookmarkAdapter.setBookmarks(bookmarksFromManager);
                    }
                });
            }
        });
    }

    @Override
    public void onBookmarkSelected(Bookmark bookmark) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bookmark.url.toString()));
        startActivity(browserIntent);
    }

    @Override
    public void onBookmarkRemoveButtonClicked(Bookmark bookmark) {
        removeBookmark(bookmark);
    }
}
