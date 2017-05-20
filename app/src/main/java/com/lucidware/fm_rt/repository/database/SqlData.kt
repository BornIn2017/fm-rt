package com.lucidware.fm_rt.repository.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lucidware.fm_rt.domain.model.order.Order
import com.lucidware.fm_rt.repository.database.model.oder.OrderDb
import io.reactivex.Single
import nl.qbusict.cupboard.CupboardFactory.cupboard

class SqlData(context: Context)
    : SQLiteOpenHelper(context, "orders.db", null, DatabaseVersions.INITIAL), OrdersPersistableData {

    init {
        cupboard().register(OrderDb::class.java)
    }

    override fun onCreate(db: SQLiteDatabase) {
        cupboard().withDatabase(db).createTables()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        cupboard().withDatabase(db).upgradeTables()
    }

    override fun updateOrders(orders: List<Order>) {
        clearAllData()
        val databaseCompartment = cupboard().withDatabase(writableDatabase)
        orders.map { databaseCompartment.put(OrderDb(it)) }
    }

    private fun clearAllData() {
        val databaseCompartment = cupboard().withDatabase(writableDatabase)
        databaseCompartment.delete(OrderDb::class.java, null)
    }

    override fun getAvailableOrders(): Single<List<Order>> {
        return Single.just(cupboard().withDatabase(readableDatabase)
                .query(OrderDb::class.java)
                .list()
                .map(::Order)
                .also {
                    if (it.isEmpty()) return Single.error { IllegalStateException("Database is empty") }
                })
    }
}