package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class PromoIconRemote(
    @Json(name = "text")
    val text: String?,
    @Json(name = "type")
    val type: String?
)