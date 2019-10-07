package com.xe72.dailygur.recycler;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.xe72.dailygur.R;
import com.xe72.dailygur.dto.InnerData;
import com.xe72.dailygur.view.CustomCropTransformation;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureHolder> {

    RequestManager requestManager;
    private OnItemClickListener clickListener;
    private final List<InnerData> imageList = new ArrayList<>();
    private int imgWidth;
    private int appHeight;

    public PicturesAdapter(RequestManager requestManager, OnItemClickListener clickListener) {
        this.requestManager = requestManager;
        this.clickListener = clickListener;
    }

    public void changeData(List<InnerData> imageList) {
        this.imageList.clear();
        this.imageList.addAll(imageList);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Context context = recyclerView.getContext();
                int IMG_MARGIN = (int) (7 * context.getResources().getDisplayMetrics().density);
                int CARD_MARGIN = (int) (5 * context.getResources().getDisplayMetrics().density);
                imgWidth = (recyclerView.getWidth() / 2) - (CARD_MARGIN * 2) - (IMG_MARGIN * 2);
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                Display display = recyclerView.getDisplay();
                Point size = new Point();
                display.getSize(size);
                appHeight = size.y;
            }
        });
    }

    @NonNull
    @Override
    public PictureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture, parent, false);
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
            int imgHeight = imgWidth * image.getCoverHeight() / image.getCoverWidth();
            ivPicture.getLayoutParams().width = imgWidth;
            if (imgHeight > appHeight * 0.8) {
                ivPicture.getLayoutParams().height = (int) (appHeight * 0.8);
            } else {
                ivPicture.getLayoutParams().height = imgHeight;
            }

            ivPicture.requestLayout();
            RequestOptions requestOptions =
                    new RequestOptions().placeholder(R.drawable.image_placeholder).transform(new CustomCropTransformation());
            requestManager.load(image.getImageLink()).apply(requestOptions).into(ivPicture);
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
