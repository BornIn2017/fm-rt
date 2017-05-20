package com.lucidware.fm_rt.presentation.application.di;

import com.lucidware.fm_rt.domain.repositories.OrdersRepository;
import com.lucidware.fm_rt.domain.use_causes.AsyncUseCaseSchedule;
import com.lucidware.fm_rt.domain.use_causes.UseCaseSchedule;
import com.lucidware.fm_rt.domain.use_causes.order.GetOrdersUseCause;

import dagger.Module;
import dagger.Provides;

@Module
class UseCaseModule {

    @Provides
    UseCaseSchedule providesUseCaseSchedule() {
        return new AsyncUseCaseSchedule();
    }

    @Provides
    GetOrdersUseCause providesGetOrdersUseCause(
            UseCaseSchedule useCaseSchedule,
            OrdersRepository ordersRepository) {
        return new GetOrdersUseCause(useCaseSchedule, ordersRepository, false);
    }
}