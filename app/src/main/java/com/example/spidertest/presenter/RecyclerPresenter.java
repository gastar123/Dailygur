package com.example.spidertest.presenter;

import android.util.Log;

import com.example.spidertest.model.RecyclerModel;
import com.example.spidertest.view.IRecyclerView;

public class RecyclerPresenter {

    private IRecyclerView view;
    private RecyclerModel model;
    private int page;
    private boolean needLoad;

    public RecyclerPresenter(IRecyclerView view, RecyclerModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        setPictureList();
    }

    public void setPictureList() {
        model.loadGallery(page + 1)
                .subscribe(imageList -> {
                    page++;
                    needLoad = true;
                    view.updateList(imageList);
                }, error -> {
                    needLoad = true;
                    Log.e("ERROR", error.getMessage(), error);
                    view.makeToast("Нет подключения к серверу");
                });
    }

    public void loadNextPage() {
        if (needLoad) {
            setPictureList();
            needLoad = false;
        }
    }
}
