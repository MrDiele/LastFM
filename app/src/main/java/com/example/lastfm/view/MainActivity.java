package com.example.lastfm.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.lastfm.R;
import com.example.lastfm.adapters.TopTracksAdapter;
import com.example.lastfm.utils.Constants;
import com.example.lastfm.view.di.TopTracksModule;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TopTracksModule topTracksModule;
    private RecyclerView recyclerView;
    private TopTracksInteractor topTracksImpl;
    private TopTracksPresenter topTracksPresenterImpl;
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

        topTracksImpl = new TopTracksInteractorImpl(retrofit);

        recyclerView = findViewById(R.id.recycler_view);
        //используем это для ускорения производительности
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TopTracksAdapter();
        recyclerView.setAdapter(mAdapter);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        topTracksPresenterImpl = new TopTracksPresenterImpl(topTracksImpl, mAdapter, recyclerView);
        swipeRefreshLayout.setOnRefreshListener(() -> topTracksPresenterImpl.getTopTracks(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY));
        swipeRefreshLayout.setRefreshing(true);
    }
}
