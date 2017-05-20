package com.lucidware.fm_rt.domain.repositories

import com.lucidware.fm_rt.domain.model.order.Order
import io.reactivex.Single

interface OrdersRepository {
    fun getOrders(forceRefresh: Boolean): Single<List<Order>>
}