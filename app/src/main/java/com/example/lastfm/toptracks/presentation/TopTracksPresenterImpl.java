package com.example.lastfm.toptracks.presentation;

import com.example.lastfm.core.data.Constants;
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.core.models.data.TopTracksResponse;
import com.example.lastfm.core.models.data.Tracks;
import com.example.lastfm.toptracks.view.TopTracksView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TopTracksPresenterImpl implements TopTracksPresenter {
    private Interactor interactor;
    private TopTracksView topTracksView;

    public TopTracksPresenterImpl(Interactor interactor, TopTracksView topTracksView) {
        this.interactor = interactor;
        this.topTracksView = topTracksView;
        getTopTracks();
    }

    @Override
    public void getTopTracks() {
        topTracksView.updateTrack(new Tracks());
        interactor.getTopTracks(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<TopTracksResponse>() {
                    @Override
                    public void onSuccess(TopTracksResponse trackList) {
                        topTracksView.updateTrack(trackList.getTracks());
                        topTracksView.hideRefresh();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        topTracksView.showError(e.getMessage());
                        topTracksView.hideRefresh();
                    }
                });
    }
}
