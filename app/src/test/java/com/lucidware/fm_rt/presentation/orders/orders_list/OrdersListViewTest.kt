package com.lucidware.fm_rt.presentation.orders.orders_list

import com.infullmobile.android.infullmvp.basetest.InFullMvpFragmentBaseTest
import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.presentation.orders.orders_list.di.OrdersListModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OrdersListViewTest : InFullMvpFragmentBaseTest<OrdersListFragment>() {

    @Test
    fun shouldBound() {
        // when
        testedFragment.presentedView.onViewsBound()

        //then
        with(testedFragment.presentedView) {
            assertEquals(ADAPTER, recyclerView.adapter)
            assertTrue(pullToRefresh.hasOnClickListeners())
        }
    }

    @Test
    fun shouldDisplayProgress() {
        // when
        testedFragment.presentedView.displayProgress()

        //then
        assertEquals(OrdersListView.CHILD_PROGRESS, testedFragment.presentedView.viewAnimator.displayedChild)
    }

    @Test
    fun shouldDisplayContent() {
        // when
        testedFragment.presentedView.displayContent()

        //then
        assertEquals(OrdersListView.CHILD_CONTENT, testedFragment.presentedView.viewAnimator.displayedChild)
    }

    @Test
    fun shouldHandleOrders() {
        // given
        val orders = emptyList<Order>()

        // when
        testedFragment.presentedView.handleOrders(orders)

        //then
        verify(ADAPTER).setOrders(orders)
    }

    override fun provideFragment(): OrdersListFragment {
        return OrdersListFragment.newInstance()
    }

    override fun substituteModules(fragment: OrdersListFragment) {
        fragment.ordersListGraph.setOrdersListModule(TestAboutMeModule(fragment))
    }

    companion object {
        val ADAPTER: OrdersRecyclerViewAdapter = mock(OrdersRecyclerViewAdapter::class.java)
    }

    class TestAboutMeModule(fragment: OrdersListFragment) : OrdersListModule(fragment) {

        override fun providesOrdersRecyclerViewAdapter(): OrdersRecyclerViewAdapter {
            return ADAPTER
        }

        override fun providesOrdersListPresenter(model: OrdersListModel, view: OrdersListView): OrdersListPresenter {
            return mock(OrdersListPresenter::class.java)
        }

    }
}