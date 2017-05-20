package com.lucidware.fm_rt.repository.orders;

import com.lucidware.fm_rt.repository.orders.model.OrdersResponseEntity;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface OrdersService {

    @GET("~dpaluch/test35/")
    Single<OrdersResponseEntity> getOrders();
}
