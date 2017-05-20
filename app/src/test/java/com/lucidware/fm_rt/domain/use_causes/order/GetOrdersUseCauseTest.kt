package com.lucidware.fm_rt.domain.use_causes.order

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.repositories.OrdersRepository
import com.lucidware.fm_rt.domain.use_causes.ImmediateUseCaseSchedule
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Matchers.anyBoolean
import org.mockito.Matchers.anyObject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when` as whenDo
import org.mockito.MockitoAnnotations

class GetOrdersUseCauseTest {

    @Mock lateinit var mockedOrdersRepository: OrdersRepository
    private lateinit var getOrdersUseCause: GetOrdersUseCause

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getOrdersUseCause = GetOrdersUseCause(ImmediateUseCaseSchedule(), mockedOrdersRepository)
    }

    @Test
    fun shouldReturnDataWhenUserLoggedIn() {
        // given
        val orders = emptyList<Order>()
        whenDo(mockedOrdersRepository.getOrders(anyBoolean()))
                .thenReturn(Single.just(orders))

        // when
        val result = getOrdersUseCause.refresh(true).performOnTheSameThread().blockingGet()

        // then
        assertEquals(result, orders)
    }
}