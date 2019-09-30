package com.example.lastfm.presentation.toptracks.view;

import com.example.lastfm.models.data.Tracks;

public interface TopTracksView {
    void updateTrack(Tracks tracks);

    void hideRefresh();

    void showError(String message);
}
