package com.example.lastfm.topartists.view;


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
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.core.models.data.Artists;
import com.example.lastfm.topartists.presentation.TopArtistPresenter;
import com.example.lastfm.topartists.presentation.TopArtistPresenterImpl;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class TopArtistsFragment extends Fragment implements TopArtistsView {
    private TopArtistsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TopArtistPresenter topArtistPresenterImpl;

    @Inject
    Interactor topArtistImpl;

    public TopArtistsFragment() {
        // Required empty public constructor
    }

    public static TopArtistsFragment newInstance() {
        return new TopArtistsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) requireActivity().getApplication()).getNetworkComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TopArtistsAdapter();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        topArtistPresenterImpl = new TopArtistPresenterImpl(topArtistImpl, this);
        swipeRefreshLayout.setOnRefreshListener(() -> topArtistPresenterImpl.getTopArtists());
        swipeRefreshLayout.setRefreshing(true);
        return view;
    }

    @Override
    public void updateArtist(Artists artists) {
        adapter.setItems(artists);
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
