package com.lucidware.fm_rt.repository.database.model.oder

import com.lucidware.fm_rt.domain.model.order.Order

class OrderDb {

    var _id: Int = -1
    var title: String = ""
    var description: String = ""
    var detailsUrl: String = ""
    var modificationDate: String = ""
    var imageUrl: String = ""

    constructor()

    constructor(order: Order) {
        this._id = order.id
        this.title = order.title
        this.description = order.description
        this.detailsUrl = order.detailsUrl
        this.modificationDate = order.modificationDate
        this.imageUrl = order.imageUrl
    }
}