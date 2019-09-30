package com.example.lastfm.di;

import com.example.lastfm.presentation.view.MainActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TopTracksModule.class})
public interface TopTrackComponent {
    void inject(MainActivity mainActivity);
}
