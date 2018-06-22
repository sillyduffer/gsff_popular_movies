package com.example.ealan.getswollforfilm.Adaptors;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ealan.getswollforfilm.Fragments.DetailsFragment;
import com.example.ealan.getswollforfilm.Fragments.ReviewFragment;
import com.example.ealan.getswollforfilm.Fragments.TrailerFragment;
import com.example.ealan.getswollforfilm.Movie;

public class FragmentAdaptor extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 3;
    private Context mContext;
    private Movie mCurrentFilm;


    public FragmentAdaptor(FragmentManager fm, Context context, Movie movie) {
        super(fm);
        mContext = context;
        mCurrentFilm = movie;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new DetailsFragment();
                break;

            case 1:
                fragment = new TrailerFragment();
                break;

            case 2:
                fragment = new ReviewFragment();
                break;

            default:
                fragment = new DetailsFragment();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public Movie getmCurrentFilm() {
        return mCurrentFilm;
    }
}
