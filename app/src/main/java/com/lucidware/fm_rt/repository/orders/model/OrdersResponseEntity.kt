package com.lucidware.fm_rt.repository.orders.model

import com.google.gson.annotations.SerializedName

data class OrdersResponseEntity(
        @SerializedName("data") var data: List<OrderEntity>
)