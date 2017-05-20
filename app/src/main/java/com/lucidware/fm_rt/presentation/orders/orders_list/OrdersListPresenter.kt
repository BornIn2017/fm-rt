package com.lucidware.fm_rt.presentation.orders.orders_list

import android.os.Bundle
import android.net.Uri
import com.lucidware.fm_rt.presentation.base.BasePresenter
import com.lucidware.fm_rt.presentation.base.register

open class OrdersListPresenter(private val model: OrdersListModel,
                               view: OrdersListView) : BasePresenter<OrdersListView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        getOrders()
    }

    open fun getOrders(forceRefresh: Boolean = false) {
        model.getOrders(forceRefresh)
                .doOnSubscribe { presentedView.displayProgress() }
                .doFinally { presentedView.displayContent() }
                .subscribe(
                        { orders -> presentedView.handleOrders(orders) },
                        { error -> presentedView.handleError(error.localizedMessage) }
                ).register(this)
    }
}
