package com.example.spidertest.view;

import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;

import java.util.List;

public interface IImageView {

    void setAlbum(Album album);

    void setImage(Image image);

    void setCommentList(List<Comment> commentList);

    void makeToast(String s);
}
