package com.xe72.dailygur.di;

import com.xe72.dailygur.view.BaseFragment;
import com.xe72.dailygur.view.ImageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = BaseModule.class)
    abstract BaseFragment baseFragmentBinder();

    @ContributesAndroidInjector(modules = ImageModule.class)
    abstract ImageFragment imageFragmentBinder();
}
