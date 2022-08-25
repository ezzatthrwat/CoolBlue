package com.example.shopping.remote.model

import com.squareup.moshi.Json

data class PromoIconRemote(
    @field:Json(name = "text")
    val text: String?,
    @field:Json(name = "type")
    val type: String?
)
