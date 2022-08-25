package com.example.shopping.presentation.search.mapper

import com.example.core.mapper.Mapper
import com.example.shopping.R
import com.example.shopping.domain.model.PromoIcon
import com.example.shopping.presentation.search.model.PromoIconUiModel
import javax.inject.Inject

class PromoIconUiMapper @Inject constructor() : Mapper<PromoIcon, PromoIconUiModel> {
    override fun map(input: PromoIcon): PromoIconUiModel {
        val isCoolBlue = input.type.lowercase().contains("coolblue")

        return PromoIconUiModel(
            promoText = input.type,
            promoTextColor = if (isCoolBlue) R.color.white else R.color.orange,
            promoBackGroundColor = if (isCoolBlue) R.color.brand_blue else R.color.red_light
        )
    }
}
