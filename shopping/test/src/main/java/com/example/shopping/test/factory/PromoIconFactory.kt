package com.example.shopping.test.factory

import com.example.core_testing.factory.DataFactory.randomInt
import com.example.core_testing.factory.DataFactory.randomString
import com.example.shopping.data.model.PromoIconEntity
import com.example.shopping.domain.model.PromoIcon
import com.example.shopping.presentation.search.model.PromoIconUiModel
import com.example.shopping.remote.model.PromoIconRemote

object PromoIconFactory {

    fun makePromoIconUi() = PromoIconUiModel(
        promoText = randomString(),
        promoTextColor = randomInt(),
        promoBackGroundColor = randomInt(),
    )

    fun makePromoIcon() = PromoIcon(
        text = randomString(),
        type = randomString()
    )

    fun makePromoIconEntity() = PromoIconEntity(
        text = randomString(),
        type = randomString()
    )

    fun makePromoIconRemote() = PromoIconRemote(
        text = randomString(),
        type = randomString()
    )

}
