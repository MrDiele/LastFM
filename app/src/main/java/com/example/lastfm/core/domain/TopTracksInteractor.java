package com.example.lastfm.core.domain;

import com.example.lastfm.core.models.data.TopTracksResponse;
import io.reactivex.Single;

public interface TopTracksInteractor {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);
}
