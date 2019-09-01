package com.example.spidertest;

import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;

import java.util.List;

public class MainPresenter {

    private MainModel mainModel;
    private MainActivity view;

    public MainPresenter(MainModel mainModel, MainActivity view) {
        this.mainModel = mainModel;
        this.view = view;
        mainModel.setMainPresenter(this);
    }

    public void loadPictureFromInternet(int page) {
        mainModel.loadGallery(page);
    }

    public void loadAlbumFromInternet(String galleryHash) {
        mainModel.loadAlbum(galleryHash);
    }

    public void loadImageFromInternet(String galleryImageHash) {
        mainModel.loadImage(galleryImageHash);
    }

    public void setPictureList(List<InnerData> pictureList) {
        view.setPictureList(pictureList);
    }

    public void setAlbum(Album album) {
        view.setAlbum(album);
    }

    public void setImage(Image image) {
        view.setImage(image);
    }

    public void setCommentList(List<Comment> commentList) {
        view.setCommentList(commentList);
    }
}
