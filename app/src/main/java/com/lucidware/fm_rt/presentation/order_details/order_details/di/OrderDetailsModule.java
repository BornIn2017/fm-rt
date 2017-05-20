package com.lucidware.fm_rt.presentation.order_details.order_details.di;

import com.lucidware.fm_rt.presentation.order_details.order_details.OrderDetailsFragment;
import com.lucidware.fm_rt.presentation.order_details.order_details.OrderDetailsPresenter;
import com.lucidware.fm_rt.presentation.order_details.order_details.OrderDetailsView;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderDetailsModule {

    private OrderDetailsFragment fragment;

    public OrderDetailsModule(OrderDetailsFragment fragment) {
        this.fragment = fragment;
    }

    @OrderDetailsScope
    @Provides
    OrderDetailsView providesOrderDetailsView() {
        return new OrderDetailsView();
    }

    @OrderDetailsScope
    @Provides
    OrderDetailsPresenter providesOrderDetailsPresenter(OrderDetailsView view) {
        return new OrderDetailsPresenter(view);
    }
}
