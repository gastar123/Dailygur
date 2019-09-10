package com.example.spidertest.di;

import android.app.Application;

import com.example.spidertest.SpiderApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = { AndroidInjectionModule.class, AppModule.class, ActivityBindingModule.class,
        NetworkModule.class })
public interface AppComponent extends AndroidInjector<SpiderApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
