package com.lucidware.fm_rt

import com.lucidware.fm_rt.domain.model.order.Order

object OrderFactory {

    fun orderA(): Order {
        return Order(OrderEntityFactory.orderEntityA())
    }

    fun orders(): List<Order> {
        return listOf(
                Order(1, "title 1", "description 1", " http://www.onet.pl/", "2013-05-17", "http://lorempixel.com/120/120/people/"),
                Order(2, "2", "", "", "", ""),
                Order(12, "12", "", "", "", ""),
                Order(32, "32", "", "", "", ""),
                Order(42, "42", "", "", "", ""),
                Order(24, "24", "", "", "", ""),
                Order(27, "27", "", "", "", ""),
                Order(28, "28", "", "", "", ""),
                Order(112, "112", "", "", "", ""),
                Order(211, "211", "", "", "", ""),
                Order(2222, "2222", "", "", "", ""),
                Order(22, "22", "", "", "", ""),
                Order(3, "3", "", "", "", "")
        )
    }
}