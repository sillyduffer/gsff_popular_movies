package com.example.ealan.getswollforfilm.Fragments;


import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ealan.getswollforfilm.Activities.DetailActivity;
import com.example.ealan.getswollforfilm.Adaptors.ReviewListAdapter;
import com.example.ealan.getswollforfilm.R;
import com.example.ealan.getswollforfilm.Review;
import com.example.ealan.getswollforfilm.Utils.JsonUtils;
import com.example.ealan.getswollforfilm.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.ealan.getswollforfilm.Activities.MainActivity.MOVIE_LOADER_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Review>>{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ReviewListAdapter mAdapter;
    private TextView mEmptyView;
    private DetailActivity mParentActivity;
    private int mMovieId;

    //    TODO replace with api key
    private final String MOVIE_DB_API_KEY = "YOUR API KEY HERE";
    private final int REVIEWS_LOADER_ID = 66;

    private ArrayList<Review> mReviewData;


    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentActivity = (DetailActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);

        // init RecyclerView
        mRecyclerView = rootView.findViewById(R.id.review_list_recycler_view);
        mLayoutManager = new LinearLayoutManager(mParentActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ReviewListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mMovieId = mParentActivity.getmMovieId();

        mParentActivity.getSupportLoaderManager().initLoader(REVIEWS_LOADER_ID, null, this);

        return rootView;

    }

    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public android.support.v4.content.Loader<ArrayList<Review>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<ArrayList<Review>>(getContext()) {
            @Nullable
            @Override
            public ArrayList<Review> loadInBackground() {
                try {
                    String result = NetworkUtils.getJSONResponseFromUrl(createRequestUrl(mMovieId));

                    if (result != null) {
                        mReviewData = JsonUtils.getReviewData(result);
                        return mReviewData;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return mReviewData;
            }

            @Override
            protected void onStartLoading() {
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<ArrayList<Review>> loader, ArrayList<Review> data) {
        mAdapter.setReviewList(data);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<ArrayList<Review>> loader) {

    }

    private String createRequestUrl(int filmId){
        return "https://api.themoviedb.org/3/movie/" + filmId + "/reviews?api_key=" + MOVIE_DB_API_KEY;
    }
}
