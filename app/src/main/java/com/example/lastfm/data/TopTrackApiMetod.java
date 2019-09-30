package com.example.lastfm.data;

import com.example.lastfm.models.data.TopTracksResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopTrackApiMetod {
    @GET("?method=tag.gettoptracks&tag=rock&format=json")
    Single<TopTracksResponse> getTracks(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);
}
