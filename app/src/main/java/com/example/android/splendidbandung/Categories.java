package com.example.android.splendidbandung;

public class Categories {

    private String title;
    private int thumbnail;


    Categories(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }


    String getTitle() { return title; }

    int getThumbnail() {
        return thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
