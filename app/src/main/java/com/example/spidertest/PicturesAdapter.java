package com.example.spidertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureHolder> {

    private Context context;
    private final List<InnerData> imageList = new ArrayList<>();

    public PicturesAdapter(Context context) {
        this.context = context;
    }

    public void changeData(List<InnerData> notesList) {
        this.imageList.clear();
        this.imageList.addAll(notesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PictureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.picture, parent, false);
        final PictureHolder pictureHolder = new PictureHolder(view);
        return pictureHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PictureHolder holder, int position) {
        holder.bindHolder(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class PictureHolder extends RecyclerView.ViewHolder {

        private ImageView ivPicture;

        public PictureHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }

        public void bindHolder(InnerData image) {
            Glide.with(context).load(image.getImageLink()).into(ivPicture);
        }
    }
}
