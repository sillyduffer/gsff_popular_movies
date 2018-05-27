package com.example.ealan.getswollforfilm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieGridAdapterViewHolder> {


    private ArrayList<Movie> mMovieList;
    private Context mContext;
    private MovieGridAdapterClickHandler mClickHandler;

    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185/";

    public interface MovieGridAdapterClickHandler {
        void onClick(Movie movie);
    }

    public MovieGridAdapter(MovieGridAdapterClickHandler handler) {
        mClickHandler = handler;
    }


    @NonNull
    @Override
    public MovieGridAdapterViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdForGridItem = R.layout.grid_item;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(layoutIdForGridItem, parent, false);

        return new MovieGridAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieGridAdapterViewHolder holder, int position) {

        String moviePoster;
        String moviePosterUrl;
        String movieTitle;

        if (position < getItemCount()) {
            Movie movie = mMovieList.get(position);
            moviePoster = movie.getmPosterPath();
            if (moviePoster != null && !moviePoster.equals("")) {
                moviePosterUrl = BASE_POSTER_URL + moviePoster;
                Picasso.with(mContext).load(moviePosterUrl).into(holder.posterView);
            }

            movieTitle = movie.getmMovieTitle();
            holder.titleView.setText(movieTitle);
        }


    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    class MovieGridAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView posterView;

        final TextView titleView;

        public MovieGridAdapterViewHolder(View itemView) {
            super(itemView);

            posterView = itemView.findViewById(R.id.grid_poster_imageView);
            titleView = itemView.findViewById(R.id.grid_title_textView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Movie movie = mMovieList.get(position);

            mClickHandler.onClick(movie);

        }
    }

    public void setMovieList(ArrayList<Movie> movies) {

        mMovieList = movies;
        notifyDataSetChanged();
    }

}
