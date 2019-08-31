package com.example.spidertest.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.spidertest.R;
import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureHolder> {

    private Context context;
    private OnItemClickListener clickListener;
    private final List<InnerData> imageList = new ArrayList<>();

    public PicturesAdapter(Context context, OnItemClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void changeData(List<InnerData> imageList) {
        this.imageList.clear();
        this.imageList.addAll(imageList);
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
            Glide.with(context).load(image.getImageLink()).override(image.getCoverWidth(), image.getCoveHeight()).into(ivPicture);
            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(imageList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(InnerData image);
    }
}
