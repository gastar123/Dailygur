package com.example.spidertest.di;

import com.example.spidertest.model.ImageModel;
import com.example.spidertest.model.RecyclerModel;
import com.example.spidertest.model.ServerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    ServerApi provideNetworkUtils() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Authorization", "Client-ID 6726248734ce3ec")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        return serverApi;
    }

    @Provides
    RecyclerModel provideRecyclerModel(ServerApi serverApi) {
        return new RecyclerModel(serverApi);
    }

    @Provides
    ImageModel provideImageModel(ServerApi serverApi) {
        return new ImageModel(serverApi);
    }
}
