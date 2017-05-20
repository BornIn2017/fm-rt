package com.lucidware.fm_rt.presentation.order_details.order_details.di;

import android.support.annotation.VisibleForTesting;

import com.lucidware.fm_rt.presentation.order_details.order_details.OrderDetailsFragment;

import dagger.Component;

public class OrderDetailsGraph {

    private final DaggerOrderDetailsGraph_OrderDetailsComponent.Builder builder;

    public OrderDetailsGraph(OrderDetailsFragment fragment) {
        this.builder = DaggerOrderDetailsGraph_OrderDetailsComponent
                .builder()
                .orderDetailsModule(new OrderDetailsModule(fragment));
    }

    public void inject(OrderDetailsFragment fragment) {
        builder.build().inject(fragment);
    }

    @VisibleForTesting
    public void setOrderDetailsModule(OrderDetailsModule module) {
        builder.orderDetailsModule(module);
    }

    @OrderDetailsScope
    @Component(
            modules = OrderDetailsModule.class
    )
    public interface OrderDetailsComponent {
        void inject(OrderDetailsFragment fragment);
    }
}
