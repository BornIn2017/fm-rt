package com.lucidware.fm_rt.presentation.orders.orders_list

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.use_causes.order.GetOrdersUseCause
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenDo

class OrdersListModelTest {

    lateinit var testedModel: OrdersListModel
    @Mock lateinit var mockedUseCase: GetOrdersUseCause

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testedModel = OrdersListModel(mockedUseCase)
    }

    @Test
    fun shouldFetchAlbumData() {
        // given
        val orders = emptyList<Order>()
        whenDo(mockedUseCase.refresh(false))
                .thenReturn(mockedUseCase)
        whenDo(mockedUseCase.perform())
                .thenReturn(Single.just(orders))

        // when
        val response = testedModel.getOrders(false).blockingGet()

        // then
        assertEquals(orders, response)
    }
}