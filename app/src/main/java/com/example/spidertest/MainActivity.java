package com.example.spidertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import dagger.android.AndroidInjection;

import android.os.Bundle;

import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    private final List<InnerData> imageList = new ArrayList<>();
    private RecyclerFragment recyclerFragment;
    private FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerFragment = new RecyclerFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgCont, recyclerFragment);
        fTrans.addToBackStack(null);
        fTrans.commit();

        loadPicture(1);
    }

    public void loadPicture(int page) {
        mainPresenter.loadPictureFromInternet(page);
    }

    public void setImageList(List<InnerData> imageList) {
        this.imageList.addAll(imageList);
        recyclerFragment.updateList(this.imageList);
    }

    public void toImageFragment(InnerData image) {
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgCont, ImageFragment.newInstance(image));
        fTrans.commit();
    }
}
