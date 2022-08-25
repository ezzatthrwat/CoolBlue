package com.example.shopping.remote.model

import com.squareup.moshi.Json

data class ReviewInformationRemote(
    @field:Json(name = "reviewSummary")
    val reviewSummaryRemote: ReviewSummaryRemote?
)
