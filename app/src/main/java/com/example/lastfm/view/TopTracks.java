package com.example.lastfm.view;

import com.example.lastfm.models.TopTracksResponse;
import io.reactivex.Single;

public interface TopTracks {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);
}
