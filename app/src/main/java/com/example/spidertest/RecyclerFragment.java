package com.example.spidertest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;

import java.util.List;

public class RecyclerFragment extends Fragment {

    private RecyclerView rvMain;
    private PicturesAdapter picturesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        picturesAdapter = new PicturesAdapter(getContext());
        rvMain = view.findViewById(R.id.rvMain);
        StaggeredGridLayoutManager staggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvMain.setAdapter(picturesAdapter);
        rvMain.setLayoutManager(staggeredVerticalLayoutManager);

        return view;
    }

    public void updateList(List<InnerData> imageList) {
        picturesAdapter.changeData(imageList);
    }
}
