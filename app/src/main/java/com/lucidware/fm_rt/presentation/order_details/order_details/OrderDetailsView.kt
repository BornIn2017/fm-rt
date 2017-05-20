package com.lucidware.fm_rt.presentation.order_details.order_details

import android.support.annotation.LayoutRes
import android.webkit.WebView
import android.widget.ViewAnimator
import com.infullmobile.android.infullmvp.PresentedFragmentView
import com.lucidware.fm_rt.R

open class OrderDetailsView()
    : PresentedFragmentView<OrderDetailsPresenter>() {

    @LayoutRes override val layoutResId = R.layout.fragment_order_details

    val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    val webView: WebView by bindView(R.id.webView)

    override fun onViewsBound() {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
    }

    open fun showDetails(detailsUrl: String) {
        viewAnimator.displayedChild = CHILD_DETAILS
        webView.loadUrl(detailsUrl)
    }

    open fun showNoDetails() {
        viewAnimator.displayedChild = CHILD_NO_DETAILS
    }

    companion object {
        const val CHILD_NO_DETAILS = 0
        const val CHILD_DETAILS = 1
    }
}
