package com.example.dailygur.di;

import com.example.dailygur.model.ImageModel;
import com.example.dailygur.model.ServerApi;
import com.example.dailygur.presenter.ImagePresenter;
import com.example.dailygur.recycler.CommentsAdapter;
import com.example.dailygur.view.IImageView;
import com.example.dailygur.view.ImageFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    ImagePresenter provideImageFragment(IImageView view, ImageModel model) {
        return new ImagePresenter(view, model);
    }

    @Provides
    IImageView provideImageView(ImageFragment view) {
        return view;
    }

    @Provides
    ImageModel provideImageModel(ServerApi serverApi) {
        return new ImageModel(serverApi);
    }

    @Provides
    CommentsAdapter provideCommentsAdapter() {
        return new CommentsAdapter();
    }
}
