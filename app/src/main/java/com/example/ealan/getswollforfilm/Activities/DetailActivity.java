package com.example.ealan.getswollforfilm.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ealan.getswollforfilm.Adaptors.FragmentAdaptor;
import com.example.ealan.getswollforfilm.Movie;
import com.example.ealan.getswollforfilm.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Movie mCurrentFilm;
    private int mMovieId;
    private static final String MOVIE_EXTRA_KEY = "current movie";

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

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentAdaptor(getSupportFragmentManager(), DetailActivity.this, mCurrentFilm ));

        String movieTitle = mCurrentFilm.getmMovieTitle();
        mMovieId = mCurrentFilm.getmMovieId();

        setTitle(movieTitle);

    }

    public Movie getmCurrentFilm() {
        return mCurrentFilm;
    }

    public int getmMovieId() {
        return mMovieId;
    }
}
