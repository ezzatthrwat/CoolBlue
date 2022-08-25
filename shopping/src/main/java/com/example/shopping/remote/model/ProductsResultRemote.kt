package com.example.shopping.remote.model

import com.squareup.moshi.Json

data class ProductsResultRemote(
    @field:Json(name = "currentPage")
    val currentPage: Int?,
    @field:Json(name = "pageCount")
    val pageCount: Int?,
    @field:Json(name = "pageSize")
    val pageSize: Int?,
    @field:Json(name = "products")
    val productRemotes: List<ProductRemote>?,
    @field:Json(name = "totalResults")
    val totalResults: Int?
)
