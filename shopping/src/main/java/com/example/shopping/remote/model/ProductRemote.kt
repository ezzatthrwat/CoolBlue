package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class ProductRemote(
    @Json(name = "availabilityState")
    val availabilityState: Int?,
    @Json(name = "coolbluesChoiceInformationTitle")
    val coolbluesChoiceInformationTitle: String?,
    @Json(name = "listPriceExVat")
    val listPriceExVat: Double?,
    @Json(name = "listPriceIncVat")
    val listPriceIncVat: Int?,
    @Json(name = "nextDayDelivery")
    val nextDayDelivery: Boolean?,
    @Json(name = "productId")
    val productId: Int?,
    @Json(name = "productImage")
    val productImage: String?,
    @Json(name = "productName")
    val productName: String?,
    @Json(name = "promoIconRemote")
    val promoIconRemote: PromoIconRemote?,
    @Json(name = "reviewInformationRemote")
    val reviewInformationRemote: ReviewInformationRemote?,
    @Json(name = "salesPriceIncVat")
    val salesPriceIncVat: Double?,
    @Json(name = "USPs")
    val uSPs: List<String?>?
)