package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class ReviewInformationRemote(
    @Json(name = "reviewSummaryRemote")
    val reviewSummaryRemote: ReviewSummaryRemote?,
    @Json(name = "reviews")
    val reviews: List<Any?>?
)