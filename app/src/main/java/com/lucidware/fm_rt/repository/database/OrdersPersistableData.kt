package com.lucidware.fm_rt.repository.database

import com.lucidware.fm_rt.domain.model.order.Order
import io.reactivex.Single

interface OrdersPersistableData {
    fun updateOrders(orders: List<Order>)
    fun getAvailableOrders(): Single<List<Order>>
}