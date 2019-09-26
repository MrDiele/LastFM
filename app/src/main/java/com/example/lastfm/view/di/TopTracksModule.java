package com.example.lastfm.view.di;

import com.example.lastfm.utils.Constants;
import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Module
public class TopTracksModule {

    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }
}
