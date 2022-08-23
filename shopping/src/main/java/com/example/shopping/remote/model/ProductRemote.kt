package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class ProductRemote(
    @Json(name = "availabilityState")
    val availabilityState: Int?,
    @Json(name = "coolbluesChoiceInformationTitle")
    val coolbluesChoiceInformationTitle: String?,
    @Json(name = "listPriceExVat")
    val listPriceExVat: Float?,
    @Json(name = "listPriceIncVat")
    val listPriceIncVat: Float?,
    @Json(name = "nextDayDelivery")
    val nextDayDelivery: Boolean?,
    @Json(name = "productId")
    val productId: Long?,
    @Json(name = "productImage")
    val productImage: String?,
    @Json(name = "productName")
    val productName: String?,
    @Json(name = "promoIconRemote")
    val promoIconRemote: PromoIconRemote?,
    @Json(name = "reviewInformationRemote")
    val reviewInformationRemote: ReviewInformationRemote?,
    @Json(name = "salesPriceIncVat")
    val salesPriceIncVat: Float?,
    @Json(name = "USPs")
    val uSPs: List<String>?
) {
    fun availabilityState() : AvailabilityStateRemote {
        return when(availabilityState) {
            2 -> AvailabilityStateRemote.AVAILABLE
            3 -> AvailabilityStateRemote.SOLD_OUT
            else -> AvailabilityStateRemote.SOLD_OUT
        }
    }
}