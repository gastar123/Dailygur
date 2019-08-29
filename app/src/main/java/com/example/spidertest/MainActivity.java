package com.example.spidertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;

import android.os.Bundle;

import com.example.spidertest.dto.Image;
import com.example.spidertest.dto.InnerData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    private List<InnerData> imageList = new ArrayList<>();
    private RecyclerFragment recyclerFragment;
    private FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter.loadPictureFromInternet();

        recyclerFragment = new RecyclerFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgCont, recyclerFragment);
        fTrans.commit();
    }

    public void setImageList(List<InnerData> imageList) {
        this.imageList = imageList;
        recyclerFragment.updateList(imageList);
    }
}
