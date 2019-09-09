package com.example.spidertest.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spidertest.R;
import com.example.spidertest.dto.InnerData;
import com.example.spidertest.presenter.RecyclerPresenter;
import com.example.spidertest.recycler.PicturesAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import dagger.android.support.AndroidSupportInjection;

public class RecyclerFragment extends Fragment implements IRecyclerView {

    @Inject
    RecyclerPresenter presenter;
    private PicturesAdapter picturesAdapter;

    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
        picturesAdapter = new PicturesAdapter(Glide.with(getActivity()), image -> ((MainActivity) getActivity()).toImageFragment(image));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler, container, false);

        init(recyclerView);

        return recyclerView;
    }

    private void init(RecyclerView recyclerView) {
        StaggeredGridLayoutManager staggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredVerticalLayoutManager.supportsPredictiveItemAnimations();

        recyclerView.setLayoutManager(staggeredVerticalLayoutManager);
        recyclerView.setAdapter(picturesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int[] lastVisiblePictures = staggeredVerticalLayoutManager.findLastCompletelyVisibleItemPositions(null);
                List<InnerData> currentList = presenter.getCurrentList();
                if ((lastVisiblePictures[0] + 1 == currentList.size() || lastVisiblePictures[1] + 1 == currentList.size())) {
                    presenter.loadNextPage();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void updateList(List<InnerData> imageList) {
        picturesAdapter.changeData(imageList);
    }

    @Override
    public void makeToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}