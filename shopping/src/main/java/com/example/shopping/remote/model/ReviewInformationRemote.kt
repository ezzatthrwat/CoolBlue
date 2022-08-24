package com.example.shopping.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ReviewInformationRemote(
    @field:Json(name = "reviewSummary")
    val reviewSummaryRemote: ReviewSummaryRemote?
)