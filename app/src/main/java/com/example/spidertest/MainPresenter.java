package com.example.spidertest;

import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private MainModel mainModel;
    private MainActivity view;
    private List<Image> imageList = new ArrayList<>();

    public MainPresenter(MainModel mainModel, MainActivity view) {
        this.mainModel = mainModel;
        this.view = view;
        mainModel.setMainPresenter(this);
    }

    public void loadPictureFromInternet() {
        mainModel.loadGallery();
    }

    public void setImageList(List<InnerData> imageList) {
        view.setImageList(imageList);
    }

    public List<Image> getImageList() {
        return imageList;
    }
}
