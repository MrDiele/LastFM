package com.example.lastfm.core.domain;

import com.example.lastfm.core.data.TopTrackApiMetod;
import com.example.lastfm.core.models.data.TopTracksResponse;
import io.reactivex.Single;
import retrofit2.Retrofit;

public class TopTracksInteractorImpl implements TopTracksInteractor {
    private Retrofit mRetrofit;

    public TopTracksInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopTrackApiMetod.class).getTracks(userName, limit, apiKey);
    }
}
