package com.lucidware.fm_rt.repository.orders

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.repositories.OrdersRepository
import io.reactivex.Single

open class RestOrdersRepository(private val ordersService: OrdersService) : OrdersRepository {

    override fun getOrders(forceRefresh: Boolean): Single<List<Order>> {
        return ordersService.orders
                .map { it.data.map(::Order) }
    }
}