package com.xe72.dailygur.di;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.xe72.dailygur.model.BaseModel;
import com.xe72.dailygur.model.ServerApi;
import com.xe72.dailygur.presenter.BasePresenter;
import com.xe72.dailygur.recycler.PicturesAdapter;
import com.xe72.dailygur.view.BaseFragment;
import com.xe72.dailygur.view.IBaseView;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseModule {

    @Provides
    BasePresenter provideBasePresenter(IBaseView view, BaseModel model) {
        return new BasePresenter(view, model);
    }

    @Provides
    IBaseView provideBaseView(BaseFragment view) {
        return view;
    }

    @Provides
    BaseModel provideBaseModel(ServerApi serverApi) {
        return new BaseModel(serverApi);
    }

    @Provides
    PicturesAdapter providePicturesAdapter(Context context, IBaseView view) {
        return new PicturesAdapter(Glide.with(context), image -> (view.getMainActivity()).toImageFragment(image));
    }

}
