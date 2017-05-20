package com.lucidware.fm_rt

import com.lucidware.fm_rt.repository.orders.model.OrderEntity

object OrderEntityFactory {

    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val ID = 1
    const val MODIFICATION_DATE = "2011-04-11"
    const val IMAGE_URL = "image_url"

    fun orderEntityA(): OrderEntity {
        return OrderEntity(TITLE, DESCRIPTION, ID, MODIFICATION_DATE, IMAGE_URL)
    }
}