package com.example.spidertest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.reactivex.Observable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.example.spidertest.dto.InnerData;
import com.example.spidertest.recycler.PicturesAdapter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private PicturesAdapter picturesAdapter;
    private List<InnerData> imageList = new ArrayList<>();
    private MainActivity mainActivity;
    public static int page = 1;
    public static boolean needLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        setRetainInstance(true);
        mainActivity = (MainActivity) getActivity();
        init(view);

        return view;
    }

    private void init(View view) {
        RecyclerView rvMain = view.findViewById(R.id.rvMain);
        StaggeredGridLayoutManager staggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredVerticalLayoutManager.supportsPredictiveItemAnimations();

        picturesAdapter = new PicturesAdapter(getContext(), new PicturesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(InnerData image) {
                mainActivity.toImageFragment(image);
            }
        });
        picturesAdapter.setHasStableIds(true);

        rvMain.setLayoutManager(staggeredVerticalLayoutManager);
        rvMain.setAdapter(picturesAdapter);
        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int[] lastVisiblePictures = staggeredVerticalLayoutManager.findLastCompletelyVisibleItemPositions(null);
                if ((lastVisiblePictures[0] == imageList.size() || lastVisiblePictures[1] == imageList.size()) && needLoad) {
                    mainActivity.loadPicture(page);
                    needLoad = false;
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void updateList(List<InnerData> imageList) {
        this.imageList = imageList;
        picturesAdapter.changeData(imageList);
    }
}