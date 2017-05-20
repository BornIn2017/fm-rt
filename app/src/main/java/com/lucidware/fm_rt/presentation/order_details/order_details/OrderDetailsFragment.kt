package com.lucidware.fm_rt.presentation.order_details.order_details

import android.os.Bundle
import com.infullmobile.android.infullmvp.InFullMvpFragment
import com.lucidware.fm_rt.presentation.order_details.order_details.di.OrderDetailsGraph
import javax.inject.Inject

class OrderDetailsFragment : InFullMvpFragment<OrderDetailsPresenter, OrderDetailsView>() {

    @Inject lateinit var orderDetailsPresenter: OrderDetailsPresenter
    @Inject lateinit var orderDetailsView: OrderDetailsView
    lateinit var orderDetailsGraph: OrderDetailsGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderDetailsGraph = OrderDetailsGraph(this)
    }

    override fun injectIntoGraph() {
        orderDetailsGraph.inject(this)
    }

    override val presenter: OrderDetailsPresenter get() = orderDetailsPresenter
    override val presentedView: OrderDetailsView get() = orderDetailsView

    companion object {
        fun newInstance(detailsUrl: String): OrderDetailsFragment {
            return OrderDetailsFragment().apply {
                if (detailsUrl.isNotBlank()) {
                    val args = Bundle()
                    args.putString(OrderDetailsPresenter.ORDER_DETAILS_URL_ARG, detailsUrl)
                    arguments = args
                }
            }
        }
    }
}
