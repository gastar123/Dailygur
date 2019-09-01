package com.example.spidertest;

import android.util.Log;

import com.annimon.stream.Stream;
import com.example.spidertest.dto.InnerData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainModel {

    private ServerApi serverApi;
    private MainPresenter mainPresenter;

    public MainModel(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void loadGallery(int page) {
        serverApi.getGallery(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allGalleryList -> {
                    List<InnerData> imageList = Stream.of(allGalleryList.getData()).map(gallery -> {
                        String id = gallery.getId();
                        String imageLink;
                        Boolean album = gallery.getIsAlbum();
                        Integer coverWidth;
                        Integer coverHeight;
                        Log.e("ERRRRRRRRRR", gallery.getId());
                        if (gallery.getIsAlbum()) {
                            imageLink = gallery.getImages().get(0).getLink();
                            coverWidth = gallery.getImages().get(0).getWidth();
                            coverHeight = gallery.getImages().get(0).getHeight();
                        } else {
                            imageLink = gallery.getLink();
                            coverWidth = gallery.getWidth();
                            coverHeight = gallery.getHeight();
                        }
                        return new InnerData(id, imageLink, album, coverWidth, coverHeight);
                    }).toList();
                    mainPresenter.setPictureList(imageList);
                    RecyclerFragment.page++;
                    RecyclerFragment.needLoad = true;
                    Log.e("PICTURE", imageList.toString());
                }, error -> {
                    RecyclerFragment.needLoad = true;
                    Log.e("ERROR", error.getMessage(), error);
                });
    }

    public void loadComment(String galleryHash) {
        serverApi.getComments(galleryHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allComments -> mainPresenter.setCommentList(allComments.getData()),
                        error -> Log.e("ERROR", error.getMessage(), error));
    }

    public void loadAlbum(String galleryHash) {
        serverApi.getAlbum(galleryHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(album -> {
                    mainPresenter.setAlbum(album.getData());
                    loadComment(galleryHash);
                }, error -> Log.e("ERROR", error.getMessage(), error));
    }

    public void loadImage(String galleryImageHash) {
        serverApi.getImage(galleryImageHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(image -> {
                    mainPresenter.setImage(image.getData());
                    loadComment(galleryImageHash);
                }, error -> Log.e("ERROR", error.getMessage(), error));
    }
}
