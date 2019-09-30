package com.example.lastfm.presentation.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.lastfm.App;
import com.example.lastfm.R;
import com.example.lastfm.domain.TopTracksInteractor;
import com.example.lastfm.models.data.Tracks;
import com.example.lastfm.presentation.toptracks.TopTracksPresenter;
import com.example.lastfm.presentation.toptracks.TopTracksPresenterImpl;
import com.example.lastfm.presentation.toptracks.view.TopTracksAdapter;
import com.example.lastfm.presentation.toptracks.view.TopTracksView;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements TopTracksView {

    private TopTracksPresenter topTracksPresenterImpl;
    private TopTracksAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    TopTracksInteractor topTracksImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getTopTrackComponent().inject(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TopTracksAdapter();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        topTracksPresenterImpl = new TopTracksPresenterImpl(topTracksImpl, this);
        swipeRefreshLayout.setOnRefreshListener(() -> topTracksPresenterImpl.getTopTracks());
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void updateTrack(Tracks tracks) {
        adapter.setItems(tracks);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(swipeRefreshLayout, message, Snackbar.LENGTH_LONG).show();
    }
}
