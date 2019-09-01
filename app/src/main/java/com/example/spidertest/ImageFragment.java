package com.example.spidertest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment {

    private List<Comment> commentList = new ArrayList<>();


    private ImageView imageView;
    private LinearLayout layImage;
    private LinearLayout layComment;
    private LinearLayout.LayoutParams lParams;
    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    public static ImageFragment newInstance(InnerData image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("id", image.getId());
        args.putBoolean("isAlbum", image.getAlbum());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        layImage = view.findViewById(R.id.layImage);
        layComment = view.findViewById(R.id.layComment);


        MainActivity mainActivity = (MainActivity) getActivity();

        savedInstanceState = getArguments();
        String id = savedInstanceState.getString("id");
        boolean isAlbum = savedInstanceState.getBoolean("isAlbum");
        if (isAlbum) {
            mainActivity.loadAlbum(id);
        } else {
            mainActivity.loadImage(id);
        }
        return view;
    }

    public void setAlbum(Album album) {
        lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        Stream.of(album.getImages()).map(imageInAlbum -> {
            lParams.gravity = Gravity.CENTER;
            lParams.gravity = Gravity.TOP;
            imageView = new ImageView(getContext());
            Glide.with(getContext()).load(imageInAlbum.getLink()).into(imageView);
            layImage.addView(imageView, lParams);
            return null;
        });
    }


    public void setImage(Image image) {
        lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        lParams.gravity = Gravity.CENTER;
        lParams.gravity = Gravity.TOP;
        imageView = new ImageView(getContext());
        Glide.with(getContext()).load(image.getLink()).into(imageView);
        layImage.addView(imageView, lParams);
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
