package com.example.lastfm.view;

import com.example.lastfm.models.TopTracksResponse;
import com.example.lastfm.network.TopTrackApiMetod;
import io.reactivex.Single;
import retrofit2.Retrofit;

public class TopTracksImpl implements TopTracks{
    Retrofit mRetrofit;

    public TopTracksImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopTrackApiMetod.class).getTracks(userName, limit, apiKey);
    }
}
