package com.lucidware.fm_rt.presentation.application.di;

import android.content.Context;

import com.lucidware.fm_rt.domain.repositories.OrdersRepository;
import com.lucidware.fm_rt.repository.database.OrdersPersistableData;
import com.lucidware.fm_rt.repository.database.SqlData;
import com.lucidware.fm_rt.repository.orders.DbCachedOrdersRepository;
import com.lucidware.fm_rt.repository.orders.OrdersService;
import com.lucidware.fm_rt.repository.orders.RestOrdersRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    OrdersPersistableData providesOrdersPersistableData(Context context) {
        return new SqlData(context);
    }

    @Provides
    @Singleton
    RestOrdersRepository providesRestOrdersRepository(OrdersService ordersService) {
        return new RestOrdersRepository(ordersService);
    }

    @Provides
    @Singleton
    OrdersRepository providesOrdersRepository(RestOrdersRepository restOrdersRepository,
                                              OrdersPersistableData ordersPersistableData) {
        return new DbCachedOrdersRepository(restOrdersRepository, ordersPersistableData);
    }
}
