package com.example.lastfm.topartists.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lastfm.core.data.Constants;
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.core.models.data.Artists;
import com.example.lastfm.core.models.data.TopArtistsResponse;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TopArtistViewModel extends ViewModel {
    private MutableLiveData<Artists> artistLiveData = new MutableLiveData<>();
    private MutableLiveData<Void> hideRefreshLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private Interactor interactor;

    public TopArtistViewModel(Interactor interactor) {
        this.interactor = interactor;
        getTopArtists();
    }

    public LiveData<Artists> getArtistLiveData() {
        return artistLiveData;
    }

    public LiveData<Void> getHideRefreshLiveData() {
        return hideRefreshLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void getTopArtists() {
        artistLiveData.setValue(new Artists());
        interactor.getTopArtists(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<TopArtistsResponse>() {
                    @Override
                    public void onSuccess(TopArtistsResponse topArtistsList) {
                        artistLiveData.postValue(topArtistsList.getArtists());
                        hideRefreshLiveData.postValue(null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideRefreshLiveData.postValue(null);
                        errorLiveData.postValue(e.getMessage());
                    }
                });
    }
}
