package com.lucidware.fm_rt.presentation.orders

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.infullmobile.android.infullmvp.bindView
import com.lucidware.fm_rt.R
import com.lucidware.fm_rt.presentation.orders.orders_list.OrdersListFragment

class MainActivity : AppCompatActivity() {

    val toolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        showOrderListFragment()
    }

    private fun showOrderListFragment() {
        val fragment = OrdersListFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.ordersListContainer, fragment)
                .commit()
    }
}
