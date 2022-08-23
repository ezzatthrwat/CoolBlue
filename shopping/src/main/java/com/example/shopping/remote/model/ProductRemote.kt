package com.example.shopping.remote.model


import com.squareup.moshi.Json

data class ProductRemote(
    @field:Json(name = "availabilityState")
    val availabilityState: Int?,
    @field:Json(name = "coolbluesChoiceInformationTitle")
    val coolbluesChoiceInformationTitle: String?,
    @field:Json(name = "listPriceExVat")
    val listPriceExVat: Float?,
    @field:Json(name = "listPriceIncVat")
    val listPriceIncVat: Float?,
    @field:Json(name = "nextDayDelivery")
    val nextDayDelivery: Boolean?,
    @field:Json(name = "productId")
    val productId: Long?,
    @field:Json(name = "productImage")
    val productImage: String?,
    @field:Json(name = "productName")
    val productName: String?,
    @field:Json(name = "promoIcon")
    val promoIconRemote: PromoIconRemote?,
    @field:Json(name = "reviewInformation")
    val reviewInformationRemote: ReviewInformationRemote?,
    @field:Json(name = "salesPriceIncVat")
    val salesPriceIncVat: Float?,
    @field:Json(name = "USPs")
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