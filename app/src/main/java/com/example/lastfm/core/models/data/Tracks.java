package com.example.lastfm.core.models.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Tracks {

    @SerializedName("track")
    private List<Track> track = new ArrayList<>();

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }
}