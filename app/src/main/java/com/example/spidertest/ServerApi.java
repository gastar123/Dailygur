package com.example.spidertest;

import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.AllAlbum;
import com.example.spidertest.dto.AllComments;
import com.example.spidertest.dto.Image;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {

//    @Headers("Authorization: Client-ID 6726248734ce3ec")
    @GET("3/gallery/top/top/day/{page}")
    Observable<AllAlbum> getGallery(@Path("page") int page);

    @GET("3/gallery/{galleryHash}/comments/top")
    Observable<AllComments> getComments(@Path("galleryHash") String galleryHash);

    @GET("3/gallery/album/{galleryHash}")
    Observable<Album.AlbumHolder> getAlbum(@Path("galleryHash") String galleryHash);

    @GET("3/gallery/image/{galleryImageHash}")
    Observable<Image.ImageHolder> getImage(@Path("galleryImageHash") String galleryImageHash);
}
