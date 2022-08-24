package com.example.shopping.remote.api

import com.example.shopping.remote.model.ProductsResultRemote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("search")
    fun getProducts(
        @Query("query") query: String,
        @Query("page") page: Int
    ) : Single<ProductsResultRemote>

}