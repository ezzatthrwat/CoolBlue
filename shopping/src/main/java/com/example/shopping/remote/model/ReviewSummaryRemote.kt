package com.example.shopping.remote.model

import com.squareup.moshi.Json

data class ReviewSummaryRemote(
    @field:Json(name = "reviewAverage")
    val reviewAverage: Float?,
    @field:Json(name = "reviewCount")
    val reviewCount: Int?
)
