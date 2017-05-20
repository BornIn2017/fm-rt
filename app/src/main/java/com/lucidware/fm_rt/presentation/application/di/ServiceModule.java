package com.lucidware.fm_rt.presentation.application.di;

import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lucidware.fm_rt.BuildConfig;
import com.lucidware.fm_rt.repository.orders.OrdersService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    private static final String HTTP_CACHE_DIR_NAME = "http-cache";
    private static final int HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    private static final int HTTP_READ_WRITE_TIMEOUT = 30;

    @Provides
    OrdersService providesOrdersService(OkHttpClient okHttpClient, Gson gson) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(OrdersService.class);
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Cache okHttpCache) {
        return new OkHttpClient()
                .newBuilder()
                .readTimeout(HTTP_READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(HTTP_READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .cache(okHttpCache)
                .build();
    }

    @Provides
    public Cache provideOkHttpCache(Context context) {
        final File cacheFile = new File(context.getApplicationContext().getCacheDir(), HTTP_CACHE_DIR_NAME);
        if (!cacheFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            cacheFile.mkdirs();
        }
        return new Cache(cacheFile, HTTP_CACHE_SIZE);
    }
}
