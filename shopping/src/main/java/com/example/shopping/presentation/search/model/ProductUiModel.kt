package com.example.shopping.presentation.search.model

import android.text.SpannedString
import com.example.core.model.TextResource

data class ProductUiModel(
    val availabilityState: AvailabilityStateUi,
    val coolbluesChoiceInformationTitle: String,
    val listPriceIncVat: SpannedString,
    val nextDayDelivery: Boolean,
    val productId: Long,
    val productImage: String,
    val productName: String,
    val promoIconType: PromoIconUiModel?,
    val reviewAverage: Float,
    val reviewCount: TextResource,
    val salesPriceIncVat: String,
    val uSPs: SpannedString
)
