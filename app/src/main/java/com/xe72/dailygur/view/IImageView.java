package com.xe72.dailygur.view;

import com.xe72.dailygur.dto.Album;
import com.xe72.dailygur.dto.Comment;
import com.xe72.dailygur.dto.Image;

import java.util.List;

public interface IImageView {

    void setAlbum(Album album);

    void setImage(Image image);

    void setCommentList(List<Comment> commentList);

    void makeToast(String s);
}
