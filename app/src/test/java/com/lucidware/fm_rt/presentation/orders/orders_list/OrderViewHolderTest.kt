package com.lucidware.fm_rt.presentation.orders.orders_list

import android.view.LayoutInflater
import android.view.View
import com.lucidware.fm_rt.OrderFactory
import com.lucidware.fm_rt.R
import com.lucidware.fm_rt.domain.model.order.Order
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class OrderViewHolderTest {

    private lateinit var orderViewHolder: OrderViewHolder

    @Before
    fun setUp() {
        orderViewHolder = OrderViewHolder(getView())
    }

    @Test
    fun shouldBindCorrectly() {
        // given
        val order = OrderFactory.orderA()

        // when
        orderViewHolder.bind(order)

        // then
        assertEquals(order.title, orderViewHolder.title.text)
        assertEquals(order.description, orderViewHolder.description.text)
        assertEquals(orderViewHolder.getFormattedDate(order.modificationDate), orderViewHolder.modificationDate.text)
        assertTrue(orderViewHolder.itemView.hasOnClickListeners())
    }

    @Test
    fun shouldActivateOnItemClick() {
        // given
        val order = OrderFactory.orderA()
        var listenerUsed = false
        val listener: (Order) -> Unit = { listenerUsed = true }
        orderViewHolder.onItemClick = listener
        orderViewHolder.bind(order)

        // when
        orderViewHolder.itemView.performClick()

        // then
        assertTrue(listenerUsed)
    }

    private fun getView(): View {
        return LayoutInflater.from(RuntimeEnvironment.application).inflate(R.layout.order_list_item, null)
    }
}