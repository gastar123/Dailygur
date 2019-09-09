package com.example.spidertest.di;

import com.example.spidertest.model.ImageModel;
import com.example.spidertest.model.ServerApi;
import com.example.spidertest.presenter.ImagePresenter;
import com.example.spidertest.view.IImageView;
import com.example.spidertest.view.ImageFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    ImagePresenter imageFragmentProvider(IImageView view, ImageModel model) {
        return new ImagePresenter(view, model);
    }

    @Provides
    IImageView imageViewProvider(ImageFragment view) {
        return view;
    }

    @Provides
    ImageModel provideImageModel(ServerApi serverApi) {
        return new ImageModel(serverApi);
    }
}
