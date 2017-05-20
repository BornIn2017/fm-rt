package com.lucidware.fm_rt.repository.orders.model

import com.google.gson.annotations.SerializedName

data class OrderEntity(
        @SerializedName("title") var title: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("orderId") var orderId: Int,
        @SerializedName("modificationDate") var modificationDate: String?,
        @SerializedName("image_url") var imageUrl: String?
) {

    val descriptionText
        get() = description.orEmpty().substringBeforeLast(HTTP).trimEnd()

    val detailsUrl: String
        get() {
            val temp = description.orEmpty().substringAfterLast(HTTP)
            if (temp.isNotEmpty()) return HTTP + temp
            else return ""
        }

    companion object {
        private const val HTTP = "http"
    }
}