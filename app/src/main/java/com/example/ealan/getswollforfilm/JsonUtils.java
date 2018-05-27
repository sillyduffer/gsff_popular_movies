package com.example.ealan.getswollforfilm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Movie> getData(String jsonMovieData) throws JSONException {
        ArrayList<Movie> posterDataArray = new ArrayList<>();
        JSONObject baseMovie = new JSONObject(jsonMovieData);

        JSONArray movieList = baseMovie.getJSONArray("results");

        for (int i = 0; i < movieList.length(); i++) {
            JSONObject movieListItem = movieList.getJSONObject(i);
            String posterPath = movieListItem.getString("poster_path");
            int movieId = movieListItem.getInt("id");
            double movieVoteAverage = movieListItem.getDouble("vote_average");
            double moviePopularity = movieListItem.getDouble("popularity");
            String movieTitle = movieListItem.getString("title");
            String movieBackDropPath = movieListItem.getString("backdrop_path");
            String movieDescription = movieListItem.getString("overview");
            String movieReleaseDate = movieListItem.getString("release_date");

            posterDataArray.add(new Movie(posterPath,movieId,movieVoteAverage,moviePopularity, movieTitle, movieBackDropPath, movieDescription, movieReleaseDate));

        }

        return posterDataArray;
    }
}
