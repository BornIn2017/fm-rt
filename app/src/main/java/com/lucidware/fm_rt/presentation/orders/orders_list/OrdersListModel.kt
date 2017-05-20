package com.lucidware.fm_rt.presentation.orders.orders_list

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.use_causes.order.GetOrdersUseCause
import io.reactivex.Single

open class OrdersListModel(private val getOrdersUseCause: GetOrdersUseCause) {

    open fun getOrders(forceRefresh: Boolean): Single<List<Order>> {
        return getOrdersUseCause.refresh(forceRefresh).perform()
    }
}
