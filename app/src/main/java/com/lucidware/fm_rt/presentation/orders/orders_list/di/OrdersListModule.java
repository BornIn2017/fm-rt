package com.lucidware.fm_rt.presentation.orders.orders_list.di;

import android.support.v4.app.FragmentManager;

import com.lucidware.fm_rt.domain.use_causes.order.GetOrdersUseCause;
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersRecyclerViewAdapter;
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersListFragment;
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersListModel;
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersListPresenter;
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersListView;

import dagger.Module;
import dagger.Provides;

@Module
public class OrdersListModule {

    private OrdersListFragment fragment;

    public OrdersListModule(OrdersListFragment fragment) {
        this.fragment = fragment;
    }

    @OrdersListScope
    @Provides
    public OrdersRecyclerViewAdapter providesOrdersRecyclerViewAdapter() {
        return new OrdersRecyclerViewAdapter();
    }

    @OrdersListScope
    @Provides
    FragmentManager providesFragmentManager() {
        return fragment.getFragmentManager();
    }

    @OrdersListScope
    @Provides
    OrdersListView providesOrdersListView(OrdersRecyclerViewAdapter adapter, FragmentManager fragmentManager) {
        return new OrdersListView(adapter, fragmentManager);
    }

    @OrdersListScope
    @Provides
    public OrdersListModel providesOrdersListModel(GetOrdersUseCause getOrdersUseCause) {
        return new OrdersListModel(getOrdersUseCause);
    }

    @OrdersListScope
    @Provides
    public OrdersListPresenter providesOrdersListPresenter(OrdersListModel model,
                                                           OrdersListView view) {
        return new OrdersListPresenter(model, view);
    }
}
