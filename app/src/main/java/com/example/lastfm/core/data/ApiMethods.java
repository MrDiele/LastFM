package com.example.lastfm.core.data;

import com.example.lastfm.core.models.data.TopArtistsResponse;
import com.example.lastfm.core.models.data.TopTracksResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMethods {
    @GET("?method=tag.gettoptracks&tag=rock&format=json")
    Single<TopTracksResponse> getTracks(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);

    @GET("?method=chart.gettopartists&format=json")
    Single<TopArtistsResponse> getArtists(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);
}
