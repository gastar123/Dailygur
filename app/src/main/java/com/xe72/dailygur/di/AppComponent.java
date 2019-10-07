package com.xe72.dailygur.di;

import android.app.Application;

import com.xe72.dailygur.DailyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = { AndroidInjectionModule.class, AppModule.class, ActivityBindingModule.class,
        NetworkModule.class })
public interface AppComponent extends AndroidInjector<DailyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
