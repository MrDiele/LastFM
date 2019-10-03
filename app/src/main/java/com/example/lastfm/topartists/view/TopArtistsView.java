package com.example.lastfm.topartists.view;

import com.example.lastfm.core.models.data.Artists;

public interface TopArtistsView {
    void updateArtist(Artists artists);

    void hideRefresh();

    void showError(String message);
}
