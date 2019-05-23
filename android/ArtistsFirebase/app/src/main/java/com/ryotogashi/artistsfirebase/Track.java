package com.ryotogashi.artistsfirebase;

import com.google.firebase.firestore.Exclude;

public class Track {
    private String trackId;
    private Artist artist;
    private String name;
    private int rate;

    public Track(){}

    public Track(Artist artist, String name, int rate){
        this.artist = artist;
        this.name = name;
        this.rate = rate;
    }

    @Exclude
    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }
}
