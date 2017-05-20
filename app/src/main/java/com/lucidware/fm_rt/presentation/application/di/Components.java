package com.lucidware.fm_rt.presentation.application.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.lucidware.fm_rt.presentation.application.FmrtApplication;

public final class Components {

    private Components() {
        throw new AssertionError("No instances.");
    }

    public static <T> T from(@NonNull HasComponent<T> activityWithComponent) {
        return activityWithComponent.getComponent();
    }

    @SuppressWarnings("unchecked")
    //We are unable to reach application in its derived type
    public static ApplicationComponent from(@NonNull Application application) {
        return ((FmrtApplication) application).getComponent();
    }
}
