package com.example.lastfm.core.di;

import com.example.lastfm.core.data.Constants;
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.core.domain.InteractorImpl;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Module
public class NetworkModule {

    @Provides
    Retrofit providesRetrofit() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    Interactor topTracksInteractor(Retrofit retrofit) {
        return new InteractorImpl(retrofit);
    }
}
