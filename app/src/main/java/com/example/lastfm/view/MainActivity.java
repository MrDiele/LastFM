package com.example.lastfm.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.lastfm.R;
import com.example.lastfm.adapters.TopTracksAdapter;
import com.example.lastfm.models.TopTracksResponse;
import com.example.lastfm.utils.Constants;
import com.google.android.material.snackbar.Snackbar;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TopTracksRepository topTracksImpl;
    private TopTracksAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        topTracksImpl = new TopTracksRepositoryImpl(retrofit);

        recyclerView = findViewById(R.id.recycler_view);
        //используем это для ускорения производительности
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TopTracksAdapter();
        recyclerView.setAdapter(mAdapter);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(() -> getTracks());
        swipeRefreshLayout.setRefreshing(true);
        getTracks();
    }

    private void getTracks()
    {
        Single<TopTracksResponse> tracks = topTracksImpl.getTopTracks(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);

        tracks.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<TopTracksResponse>() {
                    @Override
                    public void onSuccess(TopTracksResponse trackList) {
                        mAdapter.setItems(trackList);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
