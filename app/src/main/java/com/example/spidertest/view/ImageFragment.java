package com.example.spidertest.view;

import android.content.Context;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.example.spidertest.R;
import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;
import com.example.spidertest.dto.Tag;
import com.example.spidertest.presenter.ImagePresenter;
import com.example.spidertest.recycler.CommentsAdapter;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.AndroidSupportInjection;

public class ImageFragment extends Fragment implements IImageView {

    @Inject
    ImagePresenter presenter;
    private CommentsAdapter commentsAdapter;
    private MainActivity mainActivity;
    private TextView tvTitle;
    private LinearLayout layImage;
    private RecyclerView rvComment;
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

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentsAdapter = new CommentsAdapter();
    }

    //    TODO Переделать на recyclerview
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        rvComment = view.findViewById(R.id.rvComment);
        rvComment.setNestedScrollingEnabled(false);
        rvComment.setAdapter(commentsAdapter);

        mainActivity = (MainActivity) getActivity();
        tvTitle = view.findViewById(R.id.tvTitle);
        layTag = view.findViewById(R.id.layTag);
        layImage = view.findViewById(R.id.layImage);
        lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        lParams.setMargins(0, 10, 0, 0);

        savedInstanceState = getArguments();
        String id = savedInstanceState.getString("id");
        boolean isAlbum = savedInstanceState.getBoolean("isAlbum");
        if (isAlbum) {
            presenter.loadAlbum(id);
        } else {
            presenter.loadImage(id);
        }
        return view;
    }

    @Override
    public void setAlbum(Album album) {
        tvTitle.setText(album.getTitle());
        Stream.of(album.getImages()).forEach(imageInAlbum ->
                setImagesAndDescription(imageInAlbum));

        Stream.of(album.getTags()).forEach(tag ->
                addTag(tag));
    }

    @Override
    public void setImage(Image image) {
        tvTitle.setText(image.getTitle());
        setImagesAndDescription(image);

        Stream.of(image.getTags()).forEach(tag ->
                addTag(tag));
    }

    @Override
    public void setCommentList(List<Comment> commentList) {
//        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(matchParent, wrapContent);
//        lParams.setMargins(0, 10, 0, 0);
//        addComments(commentList, 0);

        commentsAdapter.changeData(commentList);
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
            rvComment.addView(view, lp);
            List<Comment> children = comment.getChildren();
            if (children != null && !children.isEmpty()) {
                addComments(children, lvl + 1);
            }
        });
    }

    private void addTag(Tag tag) {
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
