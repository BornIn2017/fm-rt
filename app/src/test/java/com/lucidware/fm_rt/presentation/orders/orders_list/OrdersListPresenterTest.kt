package com.lucidware.fm_rt.presentation.orders.orders_list

import android.os.Bundle
import com.lucidware.fm_rt.domain.model.order.Order
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenDo

class OrdersListPresenterTest {

    @Mock private lateinit var mockedModel: OrdersListModel
    @Mock private lateinit var mockedView: OrdersListView
    private lateinit var presenter: OrdersListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = OrdersListPresenter(mockedModel, mockedView)
    }

    @Test
    fun shouldGetOrders() {
        // given
        val orders = emptyList<Order>()
        val ordersSingle = Single.just(orders)
        whenDo(mockedModel.getOrders(true))
                .thenReturn(ordersSingle)

        // when
        presenter.getOrders(true)

        // then
        verify(mockedModel).getOrders(true)
        verify(mockedView).displayProgress()
        verify(mockedView).displayContent()
        verify(mockedView).handleOrders(orders)
    }

    @Test
    fun shouldShowError() {
        // given
        val message = "message"
        val exception = Exception(message)
        val singleError = Single.error<List<Order>> { exception }
        whenDo(mockedModel.getOrders(true))
                .thenReturn(singleError)

        // when
        presenter.getOrders(true)

        // then
        verify(mockedModel).getOrders(true)
        verify(mockedView).displayProgress()
        verify(mockedView).displayContent()
        verify(mockedView).handleError(message)
    }

    @Test
    fun shouldGetOrdersOnStart() {
        // given
        val orders = emptyList<Order>()
        val ordersSingle = Single.just(orders)
        whenDo(mockedModel.getOrders(false))
                .thenReturn(ordersSingle)

        // when
        presenter.bind(Bundle(), Bundle(), null)

        // then
        verify(mockedModel).getOrders(false)
        verify(mockedView).displayProgress()
        verify(mockedView).displayContent()
        verify(mockedView).handleOrders(orders)
    }
}