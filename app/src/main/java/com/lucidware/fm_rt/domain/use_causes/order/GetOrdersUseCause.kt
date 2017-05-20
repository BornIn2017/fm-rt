package com.lucidware.fm_rt.domain.use_causes.order

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.repositories.OrdersRepository
import com.lucidware.fm_rt.domain.use_causes.UseCase
import com.lucidware.fm_rt.domain.use_causes.UseCaseSchedule
import io.reactivex.Single

open class GetOrdersUseCause(useCaseSchedule: UseCaseSchedule,
                             private val ordersRepository: OrdersRepository,
                             private val forceRefresh: Boolean = false)
    : UseCase<List<Order>>(useCaseSchedule) {

    open fun refresh(forceRefresh: Boolean): GetOrdersUseCause {
        return GetOrdersUseCause(useCaseSchedule, ordersRepository, forceRefresh)
    }

    override fun doWork(): Single<List<Order>> {
        return ordersRepository.getOrders(forceRefresh)
    }
}
