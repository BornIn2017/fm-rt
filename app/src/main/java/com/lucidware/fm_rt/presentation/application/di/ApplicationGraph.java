package com.lucidware.fm_rt.presentation.application.di;

import com.lucidware.fm_rt.presentation.application.FmrtApplication;

public class ApplicationGraph {

    private final DaggerApplicationComponent.Builder componentBuilder;

    public ApplicationGraph(FmrtApplication application) {
        componentBuilder = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(application))
                .repositoryModule(new RepositoryModule())
                .serviceModule(new ServiceModule())
                .useCaseModule(new UseCaseModule());
    }

    public ApplicationComponent getComponent() {
        return componentBuilder.build();
    }
}
