package com.lucidware.fm_rt.presentation.orders.orders_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lucidware.fm_rt.R
import com.lucidware.fm_rt.domain.model.order.Order

open class OrdersRecyclerViewAdapter : RecyclerView.Adapter<OrderViewHolder>() {

    private var orders: List<Order> = ArrayList()
    open var onItemClick: (Order) -> Unit = { /*empty*/ }

    open fun setOrders(orders: List<Order>) {
        this.orders = orders.sortedBy { it.id }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.order_list_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
        holder.onItemClick = onItemClick
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}