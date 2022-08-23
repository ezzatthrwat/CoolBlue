package com.example.shopping.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class ReviewSummaryRemote(
    @field:Json(name = "reviewAverage")
    val reviewAverage: Double?,
    @field:Json(name = "reviewCount")
    val reviewCount: Int?
)