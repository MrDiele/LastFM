package com.example.lastfm.toptracks.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lastfm.core.data.Constants;
import com.example.lastfm.core.domain.Interactor;
import com.example.lastfm.core.models.data.TopTracksResponse;
import com.example.lastfm.core.models.data.Tracks;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TopTracksViewModel extends ViewModel {
    private MutableLiveData<Tracks> tracksLiveData = new MutableLiveData<>();
    private MutableLiveData<Void> hideRefreshLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private Interactor interactor;

    public TopTracksViewModel(Interactor interactor) {
        this.interactor = interactor;
        getTopTracks();
    }

    public LiveData<Tracks> getTracksLiveData() {
        return tracksLiveData;
    }

    public LiveData<Void> getHideRefreshLiveData() {
        return hideRefreshLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void getTopTracks() {
        tracksLiveData.setValue(new Tracks());
        interactor.getTopTracks(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<TopTracksResponse>() {
                    @Override
                    public void onSuccess(TopTracksResponse trackList) {
                        tracksLiveData.postValue(trackList.getTracks());
                        hideRefreshLiveData.postValue(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        hideRefreshLiveData.postValue(null);
                        errorLiveData.postValue(e.getMessage());
                    }
                });
    }
}
