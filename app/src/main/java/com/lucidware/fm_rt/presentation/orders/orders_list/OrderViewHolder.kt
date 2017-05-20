package com.lucidware.fm_rt.presentation.orders.orders_list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.infullmobile.android.infullmvp.bindView
import com.lucidware.fm_rt.R
import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.loadImage
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView by bindView(R.id.title)
    val description: TextView by bindView(R.id.description)
    val modificationDate: TextView by bindView(R.id.modificationDate)
    val image: ImageView by bindView(R.id.image)

    open var onItemClick: (Order) -> Unit = { /*empty*/ }

    open fun bind(order: Order) {
        title.text = order.title
        description.text = order.description
        modificationDate.text = getFormattedDate(order.modificationDate)
        image.loadImage(order.imageUrl, R.drawable.no_image_placeholder)

        itemView.setOnClickListener {
            onItemClick(order)
        }
    }

    open fun getFormattedDate(modificationDate: String): String {
        val oldFormat = SimpleDateFormat(BACKEND_DATE_FORMAT, Locale.getDefault())
        val date = oldFormat.parse(modificationDate)
        val format = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault())
        return format.format(date)
    }

    companion object {
        private const val BACKEND_DATE_FORMAT = "yyyy-MM-dd"
    }
}