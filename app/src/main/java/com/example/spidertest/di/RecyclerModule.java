package com.example.spidertest.di;

import com.example.spidertest.model.RecyclerModel;
import com.example.spidertest.presenter.RecyclerPresenter;
import com.example.spidertest.view.RecyclerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerModule {

    @Provides
    RecyclerPresenter recyclerPresenterProvider(RecyclerFragment view, RecyclerModel model) {
        return new RecyclerPresenter(view, model);
    }
}
