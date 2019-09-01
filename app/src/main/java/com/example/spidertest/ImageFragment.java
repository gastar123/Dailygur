package com.example.spidertest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spidertest.dto.InnerData;

public class ImageFragment extends Fragment {

    public static ImageFragment newInstance(InnerData image, MainActivity mainActivity) {
        ImageFragment fragment = new ImageFragment();
        if (image.getAlbum()) {
            mainActivity.loadAlbum(image.getId());
        } else {
            mainActivity.loadImage(image.getId());
        }
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }
}
