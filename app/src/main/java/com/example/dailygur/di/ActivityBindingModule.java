package com.example.dailygur.di;

import com.example.dailygur.view.BaseFragment;
import com.example.dailygur.view.ImageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = BaseModule.class)
    abstract BaseFragment baseFragmentBinder();

    @ContributesAndroidInjector(modules = ImageModule.class)
    abstract ImageFragment imageFragmentBinder();
}
