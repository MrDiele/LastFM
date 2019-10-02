package com.example.lastfm.core.di;

import com.example.lastfm.toptracks.view.TopTracksFragment;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TopTracksModule.class})
public interface TopTrackComponent {
    void inject(TopTracksFragment topTracksFragment);
}
