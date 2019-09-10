package com.example.spidertest.di;

import com.example.spidertest.view.ImageFragment;
import com.example.spidertest.view.RecyclerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = RecyclerModule.class)
    abstract RecyclerFragment recyclerFragmentBinder();

    @ContributesAndroidInjector(modules = ImageModule.class)
    abstract ImageFragment imageFragmentBinder();
}
