package com.example.spidertest.di;

import com.example.spidertest.model.ImageModel;
import com.example.spidertest.presenter.ImagePresenter;
import com.example.spidertest.view.ImageFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    ImagePresenter imageFragmentProvider(ImageFragment view, ImageModel model) {
        return new ImagePresenter(view, model);
    }
}
