package com.xe72.dailygur.view;

import android.os.Bundle;

import com.xe72.dailygur.R;
import com.xe72.dailygur.dto.InnerData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private BaseFragment baseFragment;
    private ImageFragment imageFragment;
    private FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseFragment = new BaseFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgCont, baseFragment);
        fTrans.commit();

    }

    public void toImageFragment(InnerData image) {
        imageFragment = ImageFragment.newInstance(image);
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgCont, imageFragment);
        fTrans.addToBackStack(null);
        fTrans.commit();
    }
}
