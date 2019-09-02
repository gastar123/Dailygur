package com.example.spidertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import dagger.android.AndroidInjection;

import android.os.Bundle;

import com.example.spidertest.dto.Album;
import com.example.spidertest.dto.Comment;
import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    private final List<InnerData> pictureList = new ArrayList<>();
    private RecyclerFragment recyclerFragment;
    private ImageFragment imageFragment;
    private FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerFragment = new RecyclerFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgCont, recyclerFragment);
        fTrans.commit();

        loadPicture(1);
    }

    public void loadPicture(int page) {
        mainPresenter.loadPictureFromInternet(page);
    }

    public void loadAlbum(String galleryHash) {
        mainPresenter.loadAlbumFromInternet(galleryHash);
    }

    public void loadImage(String galleryImageHash) {
        mainPresenter.loadImageFromInternet(galleryImageHash);
    }

    public void setPictureList(List<InnerData> pictureList) {
        this.pictureList.addAll(pictureList);
        recyclerFragment.updateList(this.pictureList);
    }

    public void setAlbum(Album album) {
        imageFragment.setAlbum(album);
    }

    public void setImage(Image image) {
        imageFragment.setImage(image);
    }

    public void setCommentList(List<Comment> commentList) {
        imageFragment.setCommentList(commentList);
    }

    public void toImageFragment(InnerData image) {
        imageFragment = ImageFragment.newInstance(image);
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgCont, imageFragment);
        fTrans.addToBackStack(null);
        fTrans.commit();
    }
}
