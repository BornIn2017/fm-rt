package com.lucidware.fm_rt.domain.model.order

import com.lucidware.fm_rt.repository.database.model.oder.OrderDb
import com.lucidware.fm_rt.repository.orders.model.OrderEntity

data class Order(val id: Int,
                 val title: String,
                 val description: String,
                 val detailsUrl: String,
                 val modificationDate: String,
                 val imageUrl: String) {

    constructor(entity: OrderEntity) : this(
            entity.orderId,
            entity.title.orEmpty(),
            entity.descriptionText,
            entity.detailsUrl,
            entity.modificationDate.orEmpty(),
            entity.imageUrl.orEmpty()
    )

    constructor(orderDb: OrderDb) : this(
            orderDb._id,
            orderDb.title,
            orderDb.description,
            orderDb.detailsUrl,
            orderDb.modificationDate,
            orderDb.imageUrl
    )
}