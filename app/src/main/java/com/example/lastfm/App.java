package com.example.lastfm;

import android.app.Application;
import com.example.lastfm.core.di.DaggerNetworkComponent;
import com.example.lastfm.core.di.NetworkComponent;
import com.example.lastfm.core.di.NetworkModule;

public class App extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
