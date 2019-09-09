package com.example.spidertest.di;

import com.example.spidertest.view.ImageFragment;
import com.example.spidertest.view.RecyclerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

//    @ContributesAndroidInjector(modules = MainModule.class)
//    abstract MainActivity mainActivityBinder();

    @ContributesAndroidInjector(modules = RecyclerModule.class)
    abstract RecyclerFragment recyclerFragmentBinder();

    @ContributesAndroidInjector(modules = ImageModule.class)
    abstract ImageFragment imageFragmentBinder();
}
