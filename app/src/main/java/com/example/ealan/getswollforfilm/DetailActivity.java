package com.example.ealan.getswollforfilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Movie mCurrentFilm;
    private static final String MOVIE_EXTRA_KEY = "current movie";
    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE_LARGE = "w500/";
    private static final String IMAGE_SIZE_REGULAR = "w342/";

    ImageView mPosterView;
    ImageView mBackdropView;
    TextView mTitleView;
    TextView mSynopsisView;
    TextView mVoterAverageView;
    TextView mReleaseDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra(MOVIE_EXTRA_KEY)) {
                mCurrentFilm = intent.getParcelableExtra(MOVIE_EXTRA_KEY);
            }
        }

        String posterPath = mCurrentFilm.getmPosterPath();
        String movieBackdropPath = mCurrentFilm.getmMovieBackDropPath();
        double movieVoterAverage = mCurrentFilm.getmMovieVoteAverage();
        String movieTitle = mCurrentFilm.getmMovieTitle();
        String movieDescription = mCurrentFilm.getmMovieDescription();
        String movieReleaseDate = mCurrentFilm.getmMovieReleaseDate();

        String voterAverageString = String.valueOf(movieVoterAverage);

        mPosterView = findViewById(R.id.details_poster_iv);
        mBackdropView = findViewById(R.id.details_backdrop_iv);
        mTitleView = findViewById(R.id.details_title_tv);
        mSynopsisView = findViewById(R.id.details_description_tv);
        mVoterAverageView = findViewById(R.id.details_average_rating_tv);
        mReleaseDateView = findViewById(R.id.details_release_date_tv);

        loadImageIntoView(posterPath, mPosterView, IMAGE_SIZE_REGULAR);
        loadImageIntoView(movieBackdropPath, mBackdropView, IMAGE_SIZE_LARGE);

        mTitleView.setText(movieTitle);
        mSynopsisView.setText(movieDescription);
        mReleaseDateView.setText(movieReleaseDate);
        mVoterAverageView.setText(voterAverageString);


    }

    private void loadImageIntoView(String path, ImageView movieIv, String size) {
        if (path != null && !path.equals("")) {
            String moviePosterUrl = BASE_IMAGE_URL + size + path;
            Picasso.with(this).load(moviePosterUrl).into(movieIv);
        }
    }


}
