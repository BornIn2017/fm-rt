package com.lucidware.fm_rt.repository.orders

import com.lucidware.fm_rt.repository.orders.model.OrdersResponseEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenDo

class RestOrdersRepositoryTest {

    @Mock lateinit var mockedOrdersService: OrdersService
    private lateinit var restOrdersRepository: RestOrdersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        restOrdersRepository = RestOrdersRepository(mockedOrdersService)
    }

    @Test
    fun shouldGetOrdersFromService() {
        // given
        whenDo(mockedOrdersService.orders)
                .thenReturn(Single.just(OrdersResponseEntity(emptyList())))

        // when
        restOrdersRepository.getOrders(false).blockingGet()

        // then
        verify(mockedOrdersService).orders
    }
}