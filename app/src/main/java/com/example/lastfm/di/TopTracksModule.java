package com.example.lastfm.di;

import com.example.lastfm.data.Constants;
import com.example.lastfm.domain.TopTracksInteractor;
import com.example.lastfm.domain.TopTracksInteractorImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TopTracksModule {

    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    TopTracksInteractor topTracksInteractor(Retrofit retrofit) {
        return new TopTracksInteractorImpl(retrofit);
    }
}
