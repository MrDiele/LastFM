package com.example.lastfm.core.di;

import com.example.lastfm.topartists.view.TopArtistsFragment;
import com.example.lastfm.toptracks.view.TopTracksFragment;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(TopArtistsFragment topArtistsFragment);

    void inject(TopTracksFragment topTracksFragment);
}
