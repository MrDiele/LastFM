package com.example.lastfm.topartists.presentation;

import com.example.lastfm.core.data.Constants;
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.core.models.data.Artists;
import com.example.lastfm.core.models.data.TopArtistsResponse;
import com.example.lastfm.topartists.view.TopArtistsView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TopArtistPresenterImpl implements TopArtistPresenter {
    private Interactor interactor;
    private TopArtistsView topArtistsView;

    public TopArtistPresenterImpl(Interactor interactor, TopArtistsView topArtistsView) {
        this.interactor = interactor;
        this.topArtistsView = topArtistsView;
        getTopArtists();
    }

    @Override
    public void getTopArtists() {
        topArtistsView.updateArtist(new Artists());
        interactor.getTopArtists(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<TopArtistsResponse>() {
                    @Override
                    public void onSuccess(TopArtistsResponse topArtistsList) {
                        topArtistsView.updateArtist(topArtistsList.getArtists());
                        topArtistsView.hideRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        topArtistsView.showError(e.getMessage());
                        topArtistsView.hideRefresh();
                    }
                });
    }
}
