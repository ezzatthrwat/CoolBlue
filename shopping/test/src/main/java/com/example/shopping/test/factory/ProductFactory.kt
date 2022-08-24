package com.example.shopping.test.factory
import android.text.SpannedString
import com.example.core.model.TextResource
import com.example.core_testing.factory.DataFactory.randomBoolean
import com.example.core_testing.factory.DataFactory.randomFloat
import com.example.core_testing.factory.DataFactory.randomString
import com.example.shopping.data.model.AvailabilityStateEntity
import com.example.shopping.data.model.ProductEntity
import com.example.shopping.domain.model.AvailabilityState
import com.example.shopping.domain.model.Product
import com.example.shopping.presentation.search.model.AvailabilityStateUi
import com.example.shopping.presentation.search.model.ProductUiModel
import com.example.shopping.remote.model.ProductRemote

object ProductFactory {

    fun makeProductUiModel() = ProductUiModel(
        availabilityState = AvailabilityStateUi.AVAILABLE,
        coolbluesChoiceInformationTitle = randomString(),
        listPriceIncVat = SpannedString.valueOf(randomString()),
        nextDayDelivery = randomBoolean(),
        productId = 1L,
        productImage = randomString(),
        productName = randomString(),
        promoIconType = PromoIconFactory.makePromoIconUi(),
        reviewAverage = randomFloat(),
        reviewCount = TextResource.fromText(randomString()),
        salesPriceIncVat = randomString(),
        uSPs = SpannedString.valueOf(randomString())
    )

    fun makeProduct() = Product(
        availabilityState = AvailabilityState.AVAILABLE,
        coolbluesChoiceInformationTitle = randomString(),
        listPriceExVat = randomFloat(),
        listPriceIncVat = randomFloat(),
        nextDayDelivery = randomBoolean(),
        productId = 1L,
        productImage = randomString(),
        productName = randomString(),
        promoIcon = PromoIconFactory.makePromoIcon(),
        reviewInformation = ReviewInformationFactory.makeReviewInformation(),
        salesPriceIncVat = randomFloat(),
        uSPs = listOf(randomString())
    )


    fun makeProductEntity() = ProductEntity(
        availabilityState = AvailabilityStateEntity.AVAILABLE,
        coolbluesChoiceInformationTitle = randomString(),
        listPriceExVat = randomFloat(),
        listPriceIncVat = randomFloat(),
        nextDayDelivery = randomBoolean(),
        productId = 1L,
        productImage = randomString(),
        productName = randomString(),
        promoIconEntity = PromoIconFactory.makePromoIconEntity(),
        reviewInformationEntity = ReviewInformationFactory.makeReviewInformationEntity(),
        salesPriceIncVat = randomFloat(),
        uSPs = listOf(randomString())
    )


    fun makeProductRemote() = ProductRemote(
        availabilityState = 2,
        coolbluesChoiceInformationTitle = randomString(),
        listPriceExVat = randomFloat(),
        listPriceIncVat = randomFloat(),
        nextDayDelivery = randomBoolean(),
        productId = 1L,
        productImage = randomString(),
        productName = randomString(),
        promoIconRemote = PromoIconFactory.makePromoIconRemote(),
        reviewInformationRemote = ReviewInformationFactory.makeReviewInformationRemote(),
        salesPriceIncVat = randomFloat(),
        uSPs = listOf(randomString())
    )


}