package com.example.dailygur.view;

import com.example.dailygur.dto.Album;
import com.example.dailygur.dto.Comment;
import com.example.dailygur.dto.Image;

import java.util.List;

public interface IImageView {

    void setAlbum(Album album);

    void setImage(Image image);

    void setCommentList(List<Comment> commentList);

    void makeToast(String s);
}
