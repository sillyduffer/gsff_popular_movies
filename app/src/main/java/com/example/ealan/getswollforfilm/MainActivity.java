package com.example.ealan.getswollforfilm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>>,MovieGridAdapter.MovieGridAdapterClickHandler {
    private static final String MOVIE_EXTRA_KEY = "current movie";

    private MovieGridAdapter mAdapter;

    private ArrayList<Movie> mMovieData;

    public static final int MOVIE_LOADER_ID = 55;

//    TODO replace with api key
    private final String MOVIE_DB_API_KEY = "YOUR_KEY_HERE";
    private final String POPULAR_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=" + MOVIE_DB_API_KEY;
    private final String RATING_URL =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=" + MOVIE_DB_API_KEY;

    private boolean sortBy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mMovieGridRecyclerView = findViewById(R.id.movie_grid_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mMovieGridRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MovieGridAdapter(this);
        mMovieGridRecyclerView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);

    }


    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<ArrayList<Movie>>(this) {
            @Nullable
            @Override
            public ArrayList<Movie> loadInBackground() {
                String movieQueryUrl;
                if (!sortBy) {
                   movieQueryUrl = POPULAR_URL;
                } else {
                    movieQueryUrl = RATING_URL;
                }

                String result;

                try {
                    result = NetworkUtils.getJSONResponseFromUrl(movieQueryUrl);

                    if (result != null) {
                        mMovieData = JsonUtils.getData(result);
                        return mMovieData;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return mMovieData;
            }

            @Override
            protected void onStartLoading() {
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
        mAdapter.setMovieList(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(MOVIE_EXTRA_KEY, movie);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popularity_option :
//                do the thing
                sortBy = false;
                getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
                return true;
            case R.id.rating_option :
//                do the other thing
                sortBy = true;
                getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
