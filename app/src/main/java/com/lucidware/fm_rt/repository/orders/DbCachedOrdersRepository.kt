package com.lucidware.fm_rt.repository.orders

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.repositories.OrdersRepository
import com.lucidware.fm_rt.repository.database.OrdersPersistableData
import io.reactivex.Single

class DbCachedOrdersRepository(private val ordersRepository: OrdersRepository,
                               private val ordersData: OrdersPersistableData) : OrdersRepository {

    override fun getOrders(forceRefresh: Boolean): Single<List<Order>> {
        return if (forceRefresh) {
            getFreshDataAndPutInCache(forceRefresh)
        } else {
            getCachedDataIfAvailable().onErrorResumeNext {
                getFreshDataAndPutInCache(forceRefresh)
            }
        }
    }

    private fun getFreshDataAndPutInCache(forceRefresh: Boolean) =
            ordersRepository.getOrders(forceRefresh)
                    .doOnSuccess { orders -> ordersData.updateOrders(orders) }

    private fun getCachedDataIfAvailable() = ordersData.getAvailableOrders()
}