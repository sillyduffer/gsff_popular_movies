package com.example.ealan.getswollforfilm;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{

    private String mPosterPath;
    private int mMovieId;
    private double mMovieVoteAverage;
    private double mMoviePopularity;
    private String mMovieTitle;
    private String mMovieBackDropPath;
    private String mMovieDescription;
    private String mMovieReleaseDate;


    public Movie(String posterPath,
                 int movieId,
                 double movieVoterAverage,
                 double moviePopularity,
                 String movieTitle,
                 String movieBackDropPath,
                 String movieDescription,
                 String movieReleaseDate) {

        mPosterPath = posterPath;
        mMovieId = movieId;
        mMovieVoteAverage = movieVoterAverage;
        mMoviePopularity = moviePopularity;
        mMovieTitle = movieTitle;
        mMovieBackDropPath = movieBackDropPath;
        mMovieDescription = movieDescription;
        mMovieReleaseDate = movieReleaseDate;
    }

    private Movie(Parcel in) {
        mPosterPath = in.readString();
        mMovieId = in.readInt();
        mMovieVoteAverage = in.readDouble();
        mMoviePopularity = in.readDouble();
        mMovieTitle = in.readString();
        mMovieBackDropPath = in.readString();
        mMovieDescription = in.readString();
        mMovieReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getmPosterPath() {
        return mPosterPath;
    }

    public int getmMovieId() {
        return mMovieId;
    }

    public double getmMovieVoteAverage() {
        return mMovieVoteAverage;
    }

    public double getmMoviePopularity() {
        return mMoviePopularity;
    }

    public String getmMovieTitle() {
        return mMovieTitle;
    }

    public String getmMovieBackDropPath() {
        return mMovieBackDropPath;
    }

    public String getmMovieDescription() {
        return mMovieDescription;
    }

    public String getmMovieReleaseDate() {
        return mMovieReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPosterPath);
        dest.writeInt(mMovieId);
        dest.writeDouble(mMovieVoteAverage);
        dest.writeDouble(mMoviePopularity);
        dest.writeString(mMovieTitle);
        dest.writeString(mMovieBackDropPath);
        dest.writeString(mMovieDescription);
        dest.writeString(mMovieReleaseDate);
    }
}
