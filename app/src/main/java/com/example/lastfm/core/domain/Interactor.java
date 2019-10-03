package com.example.lastfm.core.domain;

import com.example.lastfm.core.models.data.TopArtistsResponse;
import com.example.lastfm.core.models.data.TopTracksResponse;
import io.reactivex.Single;

public interface Interactor {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);

    Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey);
}
