package com.xe72.dailygur.di;

import com.xe72.dailygur.model.ImageModel;
import com.xe72.dailygur.model.ServerApi;
import com.xe72.dailygur.presenter.ImagePresenter;
import com.xe72.dailygur.recycler.CommentsAdapter;
import com.xe72.dailygur.view.IImageView;
import com.xe72.dailygur.view.ImageFragment;

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
