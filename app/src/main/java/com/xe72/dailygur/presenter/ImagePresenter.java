package com.xe72.dailygur.presenter;

import android.util.Log;

import com.xe72.dailygur.model.ImageModel;
import com.xe72.dailygur.view.IImageView;

public class ImagePresenter {

    private IImageView view;
    private ImageModel model;

    public ImagePresenter(IImageView view, ImageModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadAlbum(String id) {
        model.loadAlbum(id).subscribe(album -> {
            view.setAlbum(album);
            loadComment(id);
        }, error -> onError(error));
    }

    public void loadImage(String id) {
        model.loadImage(id).subscribe(image -> {
            view.setImage(image);
            loadComment(id);
        }, error -> onError(error));
    }

    private void loadComment(String id) {
        model.loadComment(id).subscribe(allComments -> view.setCommentList(allComments), error -> onError(error));
    }

    private void onError(Throwable error) {
        Log.e("ERROR", error.getMessage(), error);
        view.makeToast("Нет подключения к серверу");
    }

    public void onDestroy() {
        view = null;
    }
}
