package com.example.spidertest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.spidertest.dto.Image;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureHolder> {

    private Context context;
    private MainPresenter mainPresenter;
    private final List<Image> imageList = new ArrayList<>();

    public PicturesAdapter(Context context, MainPresenter mainPresenter) {
        this.context = context;
        this.mainPresenter = mainPresenter;
    }

    public void changeData(List<Image> notesList) {
        this.imageList.clear();
        this.imageList.addAll(notesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PictureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PictureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PictureHolder extends RecyclerView.ViewHolder {

        public PictureHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
