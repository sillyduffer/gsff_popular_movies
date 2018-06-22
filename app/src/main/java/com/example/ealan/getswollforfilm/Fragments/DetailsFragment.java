package com.example.ealan.getswollforfilm.Fragments;


import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ealan.getswollforfilm.Activities.DetailActivity;
import com.example.ealan.getswollforfilm.Adaptors.FragmentAdaptor;
import com.example.ealan.getswollforfilm.Movie;
import com.example.ealan.getswollforfilm.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private Movie mMovie;
    private DetailActivity mParentActivity;

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE_LARGE = "w500/";
    private static final String IMAGE_SIZE_REGULAR = "w342/";

    ImageView mPosterView;
    ImageView mBackdropView;
    TextView mTitleView;
    TextView mSynopsisView;
    TextView mVoterAverageView;
    TextView mReleaseDateView;
    Button mTrailerButton;
    Button mReviewsButton;
    Button mFavoriteButton;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        mParentActivity = (DetailActivity) getActivity();

        mMovie = mParentActivity.getmCurrentFilm();


        String posterPath = mMovie.getmPosterPath();
        String movieBackdropPath = mMovie.getmMovieBackDropPath();
        double movieVoterAverage = mMovie.getmMovieVoteAverage();
        String movieTitle = mMovie.getmMovieTitle();
        String movieDescription = mMovie.getmMovieDescription();
        String movieReleaseDate = mMovie.getmMovieReleaseDate();

        String voterAverageString = String.valueOf(movieVoterAverage);

        mPosterView = rootView.findViewById(R.id.details_poster_iv);
        mBackdropView = rootView.findViewById(R.id.details_backdrop_iv);
        mTitleView = rootView.findViewById(R.id.details_title_tv);
        mSynopsisView = rootView.findViewById(R.id.details_description_tv);
        mVoterAverageView = rootView.findViewById(R.id.details_average_rating_tv);
        mReleaseDateView = rootView.findViewById(R.id.details_release_date_tv);

        loadImageIntoView(posterPath, mPosterView, IMAGE_SIZE_REGULAR);
        loadImageIntoView(movieBackdropPath, mBackdropView, IMAGE_SIZE_LARGE);

        mTitleView.setText(movieTitle);
        mSynopsisView.setText(movieDescription);
        mReleaseDateView.setText(movieReleaseDate);
        mVoterAverageView.setText(voterAverageString);

        return rootView;

    }

    private void loadImageIntoView(String path, ImageView movieIv, String size) {
        if (path != null && !path.equals("")) {
            String moviePosterUrl = BASE_IMAGE_URL + size + path;
            Picasso.with(getContext()).load(moviePosterUrl).into(movieIv);
        }
    }

}
