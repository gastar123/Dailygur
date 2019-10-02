package com.example.spidertest.presenter;

import android.util.Log;

import com.example.spidertest.dto.InnerData;
import com.example.spidertest.model.BaseModel;
import com.example.spidertest.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

public class BasePresenter {

    private IBaseView view;
    private BaseModel model;
    private int page;
    private boolean needLoad;
    private List<InnerData> currentList = new ArrayList<>();

    public BasePresenter(IBaseView view, BaseModel model) {
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
                    currentList.addAll(imageList);
                    view.updateList(currentList);
                }, error -> {
                    needLoad = true;
                    Log.e("ERROR", error.getMessage(), error);
                    view.makeToast("Нет подключения к серверу");
                });
    }

    public List<InnerData> getCurrentList() {
        return currentList;
    }

    public void loadNextPage() {
        if (needLoad) {
            setPictureList();
            needLoad = false;
        }
    }

    public void onDestroy() {
        view = null;
    }
}
