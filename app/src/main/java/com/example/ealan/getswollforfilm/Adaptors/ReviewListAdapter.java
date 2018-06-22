package com.example.ealan.getswollforfilm.Adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ealan.getswollforfilm.R;
import com.example.ealan.getswollforfilm.Review;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder> {

    private ArrayList<Review> mReviewList;
    private Context mContext;

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdForListItem = R.layout.review_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);

        return new ReviewListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        String reviewAuthor;
        String reviewContent;

        if (position < getItemCount()) {
            Review review = mReviewList.get(position);
            reviewAuthor = review.getmAuthor();
            reviewContent = review.getmContent();
            holder.authorTextView.setText(reviewAuthor);
            holder.contentTextView.setText(reviewContent);
        }
    }

    @Override
    public int getItemCount() {
        return (mReviewList == null) ? 0 : mReviewList.size();
    }

    public void setReviewList(ArrayList<Review> reviewList) {
        mReviewList = reviewList;
        notifyDataSetChanged();
    }

    class ReviewListViewHolder extends RecyclerView.ViewHolder {

        TextView authorTextView;
        TextView contentTextView;

        public ReviewListViewHolder(View itemView) {
            super(itemView);

            authorTextView = itemView.findViewById(R.id.review_author_tv);
            contentTextView = itemView.findViewById(R.id.review_content_tv);

        }
    }
}
