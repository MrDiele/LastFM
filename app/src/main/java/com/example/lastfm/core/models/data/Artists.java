package com.example.lastfm.core.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Artists {
    @SerializedName("track")
    @Expose
    private List<Artist> artist = new ArrayList<>();

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> track) {
        this.artist = artist;
    }
}
