package com.example.spidertest;

import android.util.Log;

import com.annimon.stream.Stream;
import com.example.spidertest.dto.Gallery;
import com.example.spidertest.dto.Image;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainModel {

    private ServerApi serverApi;
    private MainPresenter mainPresenter;

    public MainModel(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void loadGallery() {
        serverApi.getGallery(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(galleries -> {
                    List<Image> imageList = Stream.of(galleries).map(gallery -> gallery.getImages().get(0)).toList();

                }, error -> Log.e("ERROR", error.getMessage(), error));
    }
}
