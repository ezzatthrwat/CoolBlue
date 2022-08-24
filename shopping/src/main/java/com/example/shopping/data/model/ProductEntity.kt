package com.example.shopping.data.model

data class ProductEntity(
    val availabilityState: AvailabilityStateEntity,
    val coolbluesChoiceInformationTitle: String?,
    val listPriceExVat: Float?,
    val listPriceIncVat: Float?,
    val nextDayDelivery: Boolean,
    val productId: Long,
    val productImage: String,
    val productName: String,
    val promoIconEntity: PromoIconEntity?,
    val reviewInformationEntity: ReviewInformationEntity,
    val salesPriceIncVat: Float,
    val uSPs: List<String>
)