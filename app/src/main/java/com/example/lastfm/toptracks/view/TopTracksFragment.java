package com.example.lastfm.toptracks.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.lastfm.App;
import com.example.lastfm.R;
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.presentation.view.MainActivity;
import com.example.lastfm.toptracks.presentation.TopTracksViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class TopTracksFragment extends Fragment {
    private TopTracksAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TopTracksViewModel topTracksViewModel;

    @Inject
    Interactor topTracksImpl;

    public static TopTracksFragment newInstance() {
        return new TopTracksFragment();
    }

    public TopTracksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topTracksViewModel = ViewModelProviders.of(requireActivity(),
                new ViewModelProvider.Factory() {
                    @NonNull
                    @Override
                    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                        TopTracksViewModel viewModel = new TopTracksViewModel(topTracksImpl);
                        viewModel.getTopTracks();

                        return (T) viewModel;
                    }
                }).get(TopTracksViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) requireActivity().getApplication()).getNetworkComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new TopTracksAdapter((url, name) -> ((MainActivity) requireActivity()).onClick(url, name));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> topTracksViewModel.getTopTracks());
        swipeRefreshLayout.setRefreshing(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topTracksViewModel.getTracksLiveData()
                .observe(getViewLifecycleOwner(), tracks -> adapter.setItems(tracks));

        topTracksViewModel.getHideRefreshLiveData()
                .observe(getViewLifecycleOwner(), v -> swipeRefreshLayout.setRefreshing(false));

        topTracksViewModel.getErrorLiveData()
                .observe(getViewLifecycleOwner(), error -> Snackbar.make(swipeRefreshLayout, error, Snackbar.LENGTH_LONG).show());
    }
}
