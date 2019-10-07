package com.xe72.dailygur.presenter;

import android.util.Log;

import com.xe72.dailygur.dto.InnerData;
import com.xe72.dailygur.model.BaseModel;
import com.xe72.dailygur.view.IBaseView;

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

    private void setPictureList() {
        model.loadGallery(page + 1)
                .subscribe(imageList -> {
                    page++;
                    needLoad = true;
                    for (InnerData innerData : imageList) {
                        if (!currentList.contains(innerData)) {
                            currentList.add(innerData);
                        }
                    }
//                  Это тоже самое
//                    Stream.of(imageList).filter(i -> !currentList.contains(i)).forEach(i -> currentList.add(i));
                    view.updateList(currentList);
                    view.closeRefreshing();
                }, error -> {
                    needLoad = true;
                    Log.e("ERROR", error.getMessage(), error);
                    view.makeToast("Нет подключения к серверу");
                    view.closeRefreshing();
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

    public void updateFirstPage() {
        page = 0;
        currentList.clear();
        setPictureList();

    }

    public void onDestroy() {
        view = null;
    }
}
