package com.example.spidertest.di;

import com.example.spidertest.view.MainActivity;
import com.example.spidertest.MainModel;
import com.example.spidertest.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter(MainModel mainModel, MainActivity activity) {
        return new MainPresenter(mainModel, activity);
    }
}
