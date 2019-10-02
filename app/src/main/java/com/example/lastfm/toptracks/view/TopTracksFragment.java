package com.example.lastfm.toptracks.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.lastfm.App;
import com.example.lastfm.R;
import com.example.lastfm.core.domain.TopTracksInteractor;
import com.example.lastfm.core.models.data.Tracks;
import com.example.lastfm.toptracks.presentation.TopTracksPresenter;
import com.example.lastfm.toptracks.presentation.TopTracksPresenterImpl;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopTracksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopTracksFragment extends Fragment implements TopTracksView {
    private TopTracksPresenter topTracksPresenterImpl;
    private TopTracksAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    TopTracksInteractor topTracksImpl;

    public TopTracksFragment() {
        // Required empty public constructor
    }

    public static TopTracksFragment newInstance() {
        return new TopTracksFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) requireActivity().getApplication()).getTopTrackComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TopTracksAdapter();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        topTracksPresenterImpl = new TopTracksPresenterImpl(topTracksImpl, this);
        swipeRefreshLayout.setOnRefreshListener(() -> topTracksPresenterImpl.getTopTracks());
        swipeRefreshLayout.setRefreshing(true);
        return view;
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
