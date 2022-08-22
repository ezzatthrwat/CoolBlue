package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class ReviewSummaryRemote(
    @Json(name = "reviewAverage")
    val reviewAverage: Double?,
    @Json(name = "reviewCount")
    val reviewCount: Int?
)