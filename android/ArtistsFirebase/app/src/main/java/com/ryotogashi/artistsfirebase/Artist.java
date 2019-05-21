package com.ryotogashi.artistsfirebase;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Artist {
    private String name;
    private String genre;
    private String rate;
    private Timestamp addedDate;

    public Artist() {
        // firestore will use this to read data
    }

    public Artist(String name, String genre, String rate) {
        this.name = name;
        this.genre = genre;
        this.rate = rate;
        this.addedDate = new Timestamp(new Date());
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getRate() {
        return rate;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }
}
