package com.lucidware.fm_rt.presentation.order_details.order_details

import android.net.Uri
import android.os.Bundle
import com.lucidware.fm_rt.presentation.base.BasePresenter

open class OrderDetailsPresenter(view: OrderDetailsView) : BasePresenter<OrderDetailsView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        if (intentBundle.containsKey(ORDER_DETAILS_URL_ARG)) {
            presentedView.showDetails(intentBundle.getString(ORDER_DETAILS_URL_ARG))
        } else {
            presentedView.showNoDetails()
        }
    }

    companion object {
        const val ORDER_DETAILS_URL_ARG = "order_details_url"
    }
}
