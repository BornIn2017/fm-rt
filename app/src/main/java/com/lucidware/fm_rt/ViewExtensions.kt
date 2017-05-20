package com.lucidware.fm_rt

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(imagePath: String?, @DrawableRes placeholderResId: Int) {
    if (imagePath.isNullOrBlank()) {
        setImageResource(placeholderResId)
    } else {
        Picasso.with(context)
                .load(imagePath)
                .error(placeholderResId)
                .placeholder(placeholderResId)
                .into(this)
    }
}