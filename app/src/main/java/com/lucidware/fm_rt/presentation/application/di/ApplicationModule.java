package com.lucidware.fm_rt.presentation.application.di;

import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidware.fm_rt.presentation.application.FmrtApplication;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
class ApplicationModule {

    private final FmrtApplication app;

    ApplicationModule(FmrtApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return this.app;
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
