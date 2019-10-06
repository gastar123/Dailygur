package com.example.spidertest.di;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.spidertest.model.BaseModel;
import com.example.spidertest.model.ServerApi;
import com.example.spidertest.presenter.BasePresenter;
import com.example.spidertest.recycler.PicturesAdapter;
import com.example.spidertest.view.BaseFragment;
import com.example.spidertest.view.IBaseView;

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
