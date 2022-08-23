package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class ProductsResultRemote(
    @Json(name = "currentPage")
    val currentPage: Int?,
    @Json(name = "pageCount")
    val pageCount: Int?,
    @Json(name = "pageSize")
    val pageSize: Int?,
    @Json(name = "products")
    val productRemotes: List<ProductRemote>?,
    @Json(name = "totalResults")
    val totalResults: Int?
)