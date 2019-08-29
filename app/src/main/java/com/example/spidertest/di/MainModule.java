package com.example.spidertest.di;

import android.content.Context;

import com.example.spidertest.MainActivity;
import com.example.spidertest.MainModel;
import com.example.spidertest.MainPresenter;
import com.example.spidertest.PicturesAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter(MainModel mainModel, MainActivity activity) {
        return new MainPresenter(mainModel, activity);
    }

    @Provides
    PicturesAdapter providePicturesAdapter(Context context) {
        return new PicturesAdapter(context);
    }
}
