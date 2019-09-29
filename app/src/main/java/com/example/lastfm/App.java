package com.example.lastfm;

import android.app.Application;
import com.example.lastfm.di.DaggerTopTrackComponent;
import com.example.lastfm.di.TopTrackComponent;
import com.example.lastfm.di.TopTracksModule;

public class App extends Application {

    private TopTrackComponent topTrackComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        topTrackComponent = DaggerTopTrackComponent
                .builder()
                .topTracksModule(new TopTracksModule())
                .build();
    }

    public TopTrackComponent getTopTrackComponent() {
        return topTrackComponent;
    }
}
