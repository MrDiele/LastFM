package com.example.lastfm.view;

import androidx.recyclerview.widget.RecyclerView;
import com.example.lastfm.adapters.TopTracksAdapter;
import com.example.lastfm.models.TopTracksResponse;
import com.google.android.material.snackbar.Snackbar;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

public class TopTracksPresenterImpl implements TopTracksPresenter {
    private TopTracksInteractor mInteractor;
    private TopTracksAdapter mAdapter;
    private RecyclerView recyclerView;

    public TopTracksPresenterImpl(TopTracksInteractor interactor, TopTracksAdapter adapter, RecyclerView recyclerView)
    {
        this.mInteractor =  interactor;
        this.mAdapter = adapter;
        this.recyclerView = recyclerView;
    }

    @Override
    public void getTopTracks(String userName, int limit, String apiKey) {
        mInteractor.getTopTracks(userName, limit, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<TopTracksResponse>() {
                    @Override
                    public void onSuccess(TopTracksResponse trackList) {
                        mAdapter.setItems(trackList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }
}
