package com.xe72.dailygur.view;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.xe72.dailygur.R;
import com.xe72.dailygur.dto.Album;
import com.xe72.dailygur.dto.Comment;
import com.xe72.dailygur.dto.Image;
import com.xe72.dailygur.dto.InnerData;
import com.xe72.dailygur.dto.Tag;
import com.xe72.dailygur.presenter.ImagePresenter;
import com.xe72.dailygur.recycler.CommentsAdapter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import javax.inject.Inject;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.AndroidSupportInjection;

public class ImageFragment extends Fragment implements IImageView {

    @Inject
    ImagePresenter presenter;
    @Inject
    CommentsAdapter commentsAdapter;
    private MainActivity mainActivity;
    private NestedScrollView scrollView;
    private ProgressBar pbLoading;
    private TextView tvTitle;
    private LinearLayout layImage;
    private RecyclerView rvComment;
    private FlexboxLayout layTag;
    private LinearLayout.LayoutParams lParams;
    private LinearLayout.LayoutParams videoParams;
    private LinearLayout.LayoutParams imageParams;
    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        rvComment = view.findViewById(R.id.rvComment);
        rvComment.setNestedScrollingEnabled(false);
        rvComment.setAdapter(commentsAdapter);

        mainActivity = (MainActivity) getActivity();
        scrollView = view.findViewById(R.id.scrollView);
        pbLoading = view.findViewById(R.id.pbLoading);
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

        setScrollListener();
    }

    @Override
    public void setImage(Image image) {
        tvTitle.setText(image.getTitle());
        setImagesAndDescription(image);

        Stream.of(image.getTags()).forEach(tag ->
                addTag(tag));

        setScrollListener();
    }

    @Override
    public void setCommentList(List<Comment> commentList) {
        pbLoading.setVisibility(View.GONE);
        commentsAdapter.changeData(commentList);
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
        Display display = mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int appWidth = size.x;
        int appHeight = size.y;

        if (image.getMp4() == null) {
            int imageHeight = appWidth * image.getHeight() / image.getWidth();
            ImageView imageView = new ImageView(mainActivity);
            imageParams = new LinearLayout.LayoutParams(appWidth, imageHeight);
            imageParams.setMargins(0, 10, 0, 0);
            Glide.with(mainActivity).load(image.getLink()).skipMemoryCache(true).into(imageView);
            layImage.addView(imageView, imageParams);
        } else {
            int editedVideoHeight = (int) (appHeight - 40 * mainActivity.getResources().getDisplayMetrics().density);
            int videoWidth = editedVideoHeight * image.getWidth() / image.getHeight();
            int videoHeight = appWidth * image.getHeight() / image.getWidth();
            if (videoHeight > editedVideoHeight) {
                videoParams = new LinearLayout.LayoutParams(videoWidth, editedVideoHeight);
            } else {
                videoParams = new LinearLayout.LayoutParams(appWidth, videoHeight);
            }

            PlayerView playerView = new PlayerView(mainActivity);
            SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(mainActivity);
            playerView.setPlayer(player);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mainActivity,
                    Util.getUserAgent(mainActivity, "Dailygur"));
            MediaSource videoSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(image.getMp4()));
            player.prepare(videoSource);

            layImage.addView(playerView, videoParams);
        }

        if (image.getDescription() != null) {
            TextView tvDescription = new TextView(mainActivity);
            tvDescription.setAutoLinkMask(Linkify.WEB_URLS);
            tvDescription.setText(image.getDescription());
            layImage.addView(tvDescription, lParams);
        }
    }

    private void setScrollListener() {
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.fullScroll(View.FOCUS_UP);
                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void closeProgressBar() {
        pbLoading.setVisibility(View.GONE);
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
