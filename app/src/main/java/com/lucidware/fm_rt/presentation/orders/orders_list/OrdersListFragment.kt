package com.lucidware.fm_rt.presentation.orders.orders_list

import android.os.Bundle
import com.infullmobile.android.infullmvp.InFullMvpFragment
import com.lucidware.fm_rt.presentation.orders.orders_list.di.OrdersListGraph
import javax.inject.Inject

class OrdersListFragment : InFullMvpFragment<OrdersListPresenter, OrdersListView>() {

    @Inject lateinit var ordersListPresenter: OrdersListPresenter
    @Inject lateinit var ordersListView: OrdersListView
    lateinit var ordersListGraph: OrdersListGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ordersListGraph = OrdersListGraph(this)
    }

    override fun injectIntoGraph() {
        ordersListGraph.inject(this)
    }

    override val presenter: OrdersListPresenter get() = ordersListPresenter
    override val presentedView: OrdersListView get() = ordersListView

    companion object {
        fun newInstance(): OrdersListFragment {
            return OrdersListFragment()
        }
    }
}
