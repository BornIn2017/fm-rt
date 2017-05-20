package com.lucidware.fm_rt.presentation.orders.orders_list.di;

import android.support.annotation.VisibleForTesting;

import com.lucidware.fm_rt.presentation.application.di.ApplicationComponent;
import com.lucidware.fm_rt.presentation.application.di.Components;
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersListFragment;

import dagger.Component;

public class OrdersListGraph {

    private final DaggerOrdersListGraph_OrdersListComponent.Builder builder;

    public OrdersListGraph(OrdersListFragment fragment) {
        this.builder = DaggerOrdersListGraph_OrdersListComponent
                .builder()
                .applicationComponent(Components.from(fragment.getActivity().getApplication()))
                .ordersListModule(new OrdersListModule(fragment));
    }

    public void inject(OrdersListFragment fragment) {
        builder.build().inject(fragment);
    }

    @VisibleForTesting
    public void setOrdersListModule(OrdersListModule module) {
        builder.ordersListModule(module);
    }

    @OrdersListScope
    @Component(
            dependencies = ApplicationComponent.class,
            modules = OrdersListModule.class
    )
    public interface OrdersListComponent {
        void inject(OrdersListFragment fragment);
    }
}
