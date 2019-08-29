package com.example.spidertest;

import com.example.spidertest.dto.AllGallery;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {

//    @Headers("Authorization: Client-ID 6726248734ce3ec")
    @GET("3/gallery/top/top/day/{page}")
    Observable<AllGallery> getGallery(@Path("page") int page);
}
