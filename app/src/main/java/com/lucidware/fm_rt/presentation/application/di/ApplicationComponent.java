package com.lucidware.fm_rt.presentation.application.di;

import com.lucidware.fm_rt.domain.use_causes.order.GetOrdersUseCause;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ServiceModule.class,
        UseCaseModule.class,
        RepositoryModule.class
})
public interface ApplicationComponent {
    GetOrdersUseCause providesGetOrdersUseCause();
}
