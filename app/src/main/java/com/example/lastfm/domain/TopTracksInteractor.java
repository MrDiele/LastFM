package com.example.lastfm.domain;

import com.example.lastfm.models.data.TopTracksResponse;
import io.reactivex.Single;

public interface TopTracksInteractor {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);
}
