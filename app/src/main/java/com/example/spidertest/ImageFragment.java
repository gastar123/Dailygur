package com.example.spidertest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;
import com.example.spidertest.dto.Tag;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;
import java.util.zip.Inflater;

public class ImageFragment extends Fragment {

    private MainActivity mainActivity;
    private TextView tvTitle;
    private LinearLayout layImage;
    private LinearLayout layComment;
    private FlexboxLayout layTag;
    private LinearLayout.LayoutParams lParams;
    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    private int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;

    public static ImageFragment newInstance(InnerData image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("id", image.getId());
        args.putBoolean("isAlbum", image.getAlbum());
        fragment.setArguments(args);
        return fragment;
    }

    //    TODO Переделать на recyclerview
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mainActivity = (MainActivity) getActivity();
        tvTitle = view.findViewById(R.id.tvTitle);
        layTag = view.findViewById(R.id.layTag);
        layImage = view.findViewById(R.id.layImage);
        layComment = view.findViewById(R.id.layComment);
        lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        lParams.setMargins(0, 10, 0, 0);

        savedInstanceState = getArguments();
        String id = savedInstanceState.getString("id");
        boolean isAlbum = savedInstanceState.getBoolean("isAlbum");
        if (isAlbum) {
            mainActivity.loadAlbum(id);
        } else {
            mainActivity.loadImage(id);
        }
        return view;
    }

    public void setAlbum(Album album) {
        tvTitle.setText(album.getTitle());
        Stream.of(album.getImages()).forEach(imageInAlbum ->
                setImagesAndDescription(imageInAlbum));

        Stream.of(album.getTags()).forEach(tag ->
                setTags(tag));
    }

    public void setImage(Image image) {
        tvTitle.setText(image.getTitle());
        setImagesAndDescription(image);

        Stream.of(image.getTags()).forEach(tag ->
                setTags(tag));
    }

    public void setCommentList(List<Comment> commentList) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(matchParent, wrapContent);
        lParams.setMargins(0, 10, 0, 0);
        addComments(commentList, 0);
    }

    private void addComments(List<Comment> commentList, int lvl) {
        Stream.of(commentList).forEach(comment -> {
            View view = getLayoutInflater().inflate(R.layout.comment_layout, null);
            TextView tvComment = view.findViewById(R.id.tvComment);
            TextView tvAuthor = view.findViewById(R.id.tvAuthor);
            tvComment.setText(comment.getComment());
            tvAuthor.setText(comment.getAuthor());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(lParams);
            lp.width = matchParent;
            lp.leftMargin = lParams.leftMargin + 20 * lvl;
            layComment.addView(view, lp);
            List<Comment> children = comment.getChildren();
            if (children != null && !children.isEmpty()) {
                addComments(children, lvl + 1);
            }
        });
    }

    private void setTags(Tag tag) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        lParams.setMargins(10, 10, 10, 10);
        TextView tvTag = new TextView(mainActivity);
        tvTag.setBackgroundResource(R.drawable.bg_tag);
        tvTag.setPadding(10, 0, 10, 0);
        tvTag.setText(tag.getDisplayName());
        layTag.addView(tvTag, lParams);
    }

    private void setImagesAndDescription(Image image) {
        ImageView imageView = new ImageView(mainActivity);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(mainActivity).load(image.getLink()).into(imageView);
        layImage.addView(imageView, lParams);
        if (image.getDescription() != null) {
            TextView tvDescription = new TextView(mainActivity);
            tvDescription.setAutoLinkMask(Linkify.WEB_URLS);
            tvDescription.setText(image.getDescription());
            layImage.addView(tvDescription, lParams);
        }
    }
}
