package com.example.spidertest.di;

import com.example.spidertest.view.BaseFragment;
import com.example.spidertest.view.ImageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = BaseModule.class)
    abstract BaseFragment baseFragmentBinder();

    @ContributesAndroidInjector(modules = ImageModule.class)
    abstract ImageFragment imageFragmentBinder();
}
