package com.lucidware.fm_rt.presentation.orders.orders_list

import android.widget.LinearLayout
import com.lucidware.fm_rt.OrderFactory
import com.lucidware.fm_rt.domain.model.order.Order
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class OrdersRecyclerViewAdapterTest {

    @Mock lateinit var mockedOrderViewHolder: OrderViewHolder
    private lateinit var adapter: OrdersRecyclerViewAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        adapter = OrdersRecyclerViewAdapter()
    }

    @Test
    fun shouldGiveCorrectCount() {
        // given
        val orders = listOf(OrderFactory.orderA())
        adapter.setOrders(orders)

        // when
        val itemsCount = adapter.itemCount

        //then
        assertEquals(orders.size, itemsCount)
    }

    @Test
    fun shouldCreateViewHolder() {
        // when
        val holder = adapter.onCreateViewHolder(LinearLayout(RuntimeEnvironment.application), 0)

        // then
        assertTrue(holder is OrderViewHolder)
    }

    @Test
    fun shouldBindCorrectly() {
        // given
        val onItemClick: (Order) -> Unit = { }
        val orders = listOf(OrderFactory.orderA())
        adapter.setOrders(orders)
        adapter.onItemClick = onItemClick

        // when
        adapter.onBindViewHolder(mockedOrderViewHolder, 0)

        // then
        verify(mockedOrderViewHolder).bind(orders[0])
        verify(mockedOrderViewHolder).onItemClick = onItemClick
    }
}