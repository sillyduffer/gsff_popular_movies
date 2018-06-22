package com.example.ealan.getswollforfilm;

public class Review {
    private String mAuthor;
    private String mContent;

    public Review(String author, String content){
        mAuthor = author;
        mContent = content;
    }


    public String getmAuthor() {
        return mAuthor;
    }

    public String getmContent() {
        return mContent;
    }
}
