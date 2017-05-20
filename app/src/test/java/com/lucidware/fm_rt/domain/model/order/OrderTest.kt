package com.lucidware.fm_rt.domain.model.order

import com.lucidware.fm_rt.OrderEntityFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class OrderTest {

    @Test
    fun shouldMakeFromOrderEntity() {
        // given
        val orderEntity = OrderEntityFactory.orderEntityA()

        // when
        val order = Order(orderEntity)

        // then
        assertEquals(orderEntity.orderId, order.id)
        assertEquals(orderEntity.title, order.title)
        assertEquals(orderEntity.descriptionText, order.description)
        assertEquals(orderEntity.detailsUrl, order.detailsUrl)
        assertEquals(orderEntity.modificationDate, order.modificationDate)
        assertEquals(orderEntity.imageUrl, order.imageUrl)
    }
}