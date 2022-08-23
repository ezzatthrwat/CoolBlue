package com.example.shopping.domain.model

data class Product(
    val availabilityState: AvailabilityState,
    val coolbluesChoiceInformationTitle: String?,
    val listPriceExVat: Float?,
    val listPriceIncVat: Float?,
    val nextDayDelivery: Boolean,
    val productId: Long,
    val productImage: String,
    val productName: String,
    val promoIcon: PromoIcon?,
    val reviewInformation: ReviewInformation?,
    val salesPriceIncVat: Float,
    val uSPs: List<String>
)