package com.example.lastfm.core.domain;

import com.example.lastfm.core.data.ApiMethods;
import com.example.lastfm.core.models.data.TopArtistsResponse;
import com.example.lastfm.core.models.data.TopTracksResponse;
import io.reactivex.Single;
import retrofit2.Retrofit;

public class InteractorImpl implements Interactor {
    private Retrofit mRetrofit;

    public InteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey) {
        return mRetrofit.create(ApiMethods.class).getTracks(userName, limit, apiKey);
    }

    @Override
    public Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey) {
        return mRetrofit.create(ApiMethods.class).getArtists(userName, limit, apiKey);
    }
}
