package com.example.lastfm.toptracks.view;

import com.example.lastfm.core.models.data.Tracks;

public interface TopTracksView {
    void updateTrack(Tracks tracks);

    void hideRefresh();

    void showError(String message);
}
