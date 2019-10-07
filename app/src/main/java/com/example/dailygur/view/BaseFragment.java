package com.example.dailygur.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dailygur.R;
import com.example.dailygur.dto.InnerData;
import com.example.dailygur.presenter.BasePresenter;
import com.example.dailygur.recycler.PicturesAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.support.AndroidSupportInjection;

public class BaseFragment extends Fragment implements IBaseView {

    @Inject
    BasePresenter presenter;
    @Inject
    PicturesAdapter picturesAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

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
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = view.findViewById(R.id.rvMain);
        swipeRefreshLayout = view.findViewById(R.id.pullToRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.updateFirstPage();
        });
        init(recyclerView);

        return view;
    }

    private void init(RecyclerView recyclerView) {
        StaggeredGridLayoutManager staggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        staggeredVerticalLayoutManager.supportsPredictiveItemAnimations();

        recyclerView.setLayoutManager(staggeredVerticalLayoutManager);
        recyclerView.setAdapter(picturesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int[] lastVisiblePictures = staggeredVerticalLayoutManager.findLastCompletelyVisibleItemPositions(null);
                List<InnerData> currentList = presenter.getCurrentList();
                if ((lastVisiblePictures[0] + 1 == currentList.size()
                        || lastVisiblePictures[1] + 1 == currentList.size())) {
                    presenter.loadNextPage();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
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
    public void closeRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}