package com.lucidware.fm_rt.presentation.orders.orders_list

import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ViewAnimator
import com.infullmobile.android.infullmvp.PresentedFragmentView
import com.lucidware.fm_rt.R
import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.presentation.order_details.order_details.OrderDetailsFragment

open class OrdersListView(private val adapter: OrdersRecyclerViewAdapter,
                          private val supportFragmentManager: FragmentManager)
    : PresentedFragmentView<OrdersListPresenter>() {

    @LayoutRes override val layoutResId = R.layout.fragment_orders_list

    private var inTwoPaneMode: Boolean = false

    val snackBarContainer: ViewGroup by bindView(R.id.contentView)
    val recyclerView: RecyclerView by bindView(R.id.orderList)
    val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    val pullToRefresh: SwipeRefreshLayout by bindView(R.id.pullToRefresh)

    override fun onViewsBound() {
        recyclerView.adapter = adapter
        pullToRefresh.setOnRefreshListener {
            presenter.getOrders(true)
        }
        inTwoPaneMode = (viewFinder.invoke(this, R.id.orderDetailContainer) != null)
        adapter.onItemClick = onItemClick()
    }

    private fun onItemClick(): (Order) -> Unit {
        return { order ->
            if (inTwoPaneMode) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.orderDetailContainer, OrderDetailsFragment.newInstance(order.detailsUrl))
                        .commit()
            } else {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.ordersListContainer, OrderDetailsFragment.newInstance(order.detailsUrl))
                        .addToBackStack("details")
                        .commit()
            }
        }
    }

    open fun displayProgress() {
        viewAnimator.displayedChild = CHILD_PROGRESS
    }

    open fun displayContent() {
        pullToRefresh.isRefreshing = false
        viewAnimator.displayedChild = CHILD_CONTENT
    }

    open fun handleOrders(orders: List<Order>) {
        adapter.setOrders(orders)
    }

    open fun handleError(errorMessage: String) {
        Snackbar.make(snackBarContainer, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val CHILD_PROGRESS = 0
        const val CHILD_CONTENT = 1
    }
}
