package com.example.spidertest.model;

import android.util.Log;

import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ImageModel {

    private ServerApi serverApi;

    public ImageModel(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    public Observable<List<Comment>> loadComment(String galleryHash) {
        return serverApi.getComments(galleryHash)
                .observeOn(AndroidSchedulers.mainThread())
                .map(allComments -> allComments.getData());
    }

    public Observable<Album> loadAlbum(String galleryHash) {
        return serverApi.getAlbum(galleryHash)
                .observeOn(AndroidSchedulers.mainThread())
                .map(album -> album.getData());
    }

    public Observable<Image> loadImage(String galleryImageHash) {
        return serverApi.getImage(galleryImageHash)
                .observeOn(AndroidSchedulers.mainThread())
                .map(image -> image.getData());
    }
}
