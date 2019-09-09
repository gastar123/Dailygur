package com.example.spidertest.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import dagger.android.support.AndroidSupportInjection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.spidertest.R;
import com.example.spidertest.dto.InnerData;
import com.example.spidertest.presenter.RecyclerPresenter;
import com.example.spidertest.recycler.PicturesAdapter;
import com.example.spidertest.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerFragment extends Fragment implements IRecyclerView {

    @Inject
    RecyclerPresenter presenter;
    private PicturesAdapter picturesAdapter;
    private List<InnerData> imageList = new ArrayList<>();
    private MainActivity mainActivity;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler, container, false);

        savedInstanceState = getArguments();

        setRetainInstance(true);
        mainActivity = (MainActivity) getActivity();
        init(recyclerView, savedInstanceState);

        return recyclerView;
    }

    private void init(RecyclerView recyclerView, Bundle savedInstanceState) {
        StaggeredGridLayoutManager staggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredVerticalLayoutManager.supportsPredictiveItemAnimations();

        if (savedInstanceState == null) {
            picturesAdapter = new PicturesAdapter(Glide.with(recyclerView.getContext()), image -> mainActivity.toImageFragment(image));
        }

        recyclerView.setLayoutManager(staggeredVerticalLayoutManager);
        recyclerView.setAdapter(picturesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int[] lastVisiblePictures = staggeredVerticalLayoutManager.findLastCompletelyVisibleItemPositions(null);
                if ((lastVisiblePictures[0] + 1 == imageList.size() || lastVisiblePictures[1] + 1 == imageList.size())) {
                    presenter.loadNextPage();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void updateList(List<InnerData> imageList) {
        this.imageList.addAll(imageList);
        picturesAdapter.changeData(this.imageList);
    }

    @Override
    public void makeToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        Bundle bundle = new Bundle();
        this.setArguments(bundle);
        super.onDestroyView();

    }
}