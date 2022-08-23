package com.example.shopping.presentation.search.model

import android.text.SpannedString

data class ProductUiModel(
    val availabilityState: AvailabilityStateUi,
    val coolbluesChoiceInformationTitle: String,
    val listPriceIncVat: SpannedString,
    val nextDayDelivery: Boolean,
    val productId: Long,
    val productImage: String,
    val productName: String,
    val promoIconType: String,
    val reviewAverage: Float,
    val reviewCount: String,
    val salesPriceIncVat: String,
    val uSPs: SpannedString
)
