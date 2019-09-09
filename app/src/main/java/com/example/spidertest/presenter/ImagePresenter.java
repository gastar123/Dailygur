package com.example.spidertest.presenter;

import com.example.spidertest.model.ImageModel;
import com.example.spidertest.view.IImageView;

public class ImagePresenter {

    private IImageView view;
    private ImageModel model;

    public ImagePresenter(IImageView view, ImageModel model) {
        this.view = view;
        this.model = model;
    }
}
