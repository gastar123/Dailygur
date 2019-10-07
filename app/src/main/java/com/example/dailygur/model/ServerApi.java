package com.example.dailygur.model;

import com.example.dailygur.dto.Album;
import com.example.dailygur.dto.AllAlbum;
import com.example.dailygur.dto.AllComments;
import com.example.dailygur.dto.Image;

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
