package com.lucidware.fm_rt.repository.orders

import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.domain.repositories.OrdersRepository
import com.lucidware.fm_rt.repository.database.OrdersPersistableData
import com.lucidware.fm_rt.repository.orders.model.OrdersResponseEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenDo

class DbCachedOrdersRepositoryTest {

    @Mock lateinit var mockedPersistableData: OrdersPersistableData
    @Mock lateinit var mockedOrdersRepository: OrdersRepository
    private lateinit var dbCachedOrdersRepository: DbCachedOrdersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dbCachedOrdersRepository = DbCachedOrdersRepository(mockedOrdersRepository, mockedPersistableData)
    }

    @Test
    fun shouldGetOrdersFromRepositoryAndUpdateDb() {
        // given
        val orders = emptyList<Order>()
        whenDo(mockedOrdersRepository.getOrders(true))
                .thenReturn(Single.just(orders))

        // when
        dbCachedOrdersRepository.getOrders(true).blockingGet()

        // then
        verify(mockedOrdersRepository).getOrders(true)
        verify(mockedPersistableData).updateOrders(orders)
    }

    @Test
    fun shouldGetOrdersFromDb() {
        // given
        val orders = emptyList<Order>()
        whenDo(mockedPersistableData.getAvailableOrders())
                .thenReturn(Single.just(orders))

        // when
        dbCachedOrdersRepository.getOrders(false).blockingGet()

        // then
        verify(mockedPersistableData).getAvailableOrders()
        verifyZeroInteractions(mockedOrdersRepository)
    }

    @Test
    fun shouldGetOrdersFromRepositoryAfterNoInDb() {
        // given
        whenDo(mockedPersistableData.getAvailableOrders())
                .thenReturn(Single.error { Exception() })
        val orders = emptyList<Order>()
        whenDo(mockedOrdersRepository.getOrders(false))
                .thenReturn(Single.just(orders))

        // when
        dbCachedOrdersRepository.getOrders(false).blockingGet()

        // then
        verify(mockedPersistableData).getAvailableOrders()
        verify(mockedOrdersRepository).getOrders(false)
    }
}