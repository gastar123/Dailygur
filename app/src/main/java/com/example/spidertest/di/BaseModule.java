package com.example.spidertest.di;

import com.example.spidertest.model.BaseModel;
import com.example.spidertest.model.ServerApi;
import com.example.spidertest.presenter.BasePresenter;
import com.example.spidertest.view.BaseFragment;
import com.example.spidertest.view.IBaseView;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseModule {

    @Provides
    BasePresenter recyclerPresenterProvider(IBaseView view, BaseModel model) {
        return new BasePresenter(view, model);
    }

    @Provides
    IBaseView imageViewProvider(BaseFragment view) {
        return view;
    }

    @Provides
    BaseModel provideRecyclerModel(ServerApi serverApi) {
        return new BaseModel(serverApi);
    }
}
