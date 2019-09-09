package com.example.spidertest;

import android.util.Log;

import com.annimon.stream.Stream;
import com.example.spidertest.dto.InnerData;
import com.example.spidertest.model.ServerApi;
import com.example.spidertest.view.RecyclerFragment;

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

    public void loadComment(String galleryHash) {
        serverApi.getComments(galleryHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allComments -> mainPresenter.setCommentList(allComments.getData()),
                        error -> {
                            Log.e("ERROR", error.getMessage(), error);
                            mainPresenter.makeToast();
                        });
    }

    public void loadAlbum(String galleryHash) {
        serverApi.getAlbum(galleryHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(album -> {
                    mainPresenter.setAlbum(album.getData());
                    loadComment(galleryHash);
                }, error -> {
                    Log.e("ERROR", error.getMessage(), error);
                    mainPresenter.makeToast();
                });
    }

    public void loadImage(String galleryImageHash) {
        serverApi.getImage(galleryImageHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(image -> {
                    mainPresenter.setImage(image.getData());
                    loadComment(galleryImageHash);
                }, error -> {
                    Log.e("ERROR", error.getMessage(), error);
                    mainPresenter.makeToast();
                });
    }
}
