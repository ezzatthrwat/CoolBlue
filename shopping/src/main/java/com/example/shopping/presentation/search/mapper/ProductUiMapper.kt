package com.example.shopping.presentation.search.mapper

import android.text.SpannedString
import android.text.style.StrikethroughSpan
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.example.core.mapper.Mapper
import com.example.shopping.domain.model.Product
import com.example.shopping.presentation.search.model.AvailabilityStateUi
import com.example.shopping.presentation.search.model.ProductUiModel
import javax.inject.Inject

class ProductUiMapper @Inject constructor(
    private val promoIconUiMapper: PromoIconUiMapper
) : Mapper<Product, ProductUiModel> {

    override fun map(input: Product): ProductUiModel {
        return ProductUiModel(
            productId = input.productId,
            availabilityState = AvailabilityStateUi.valueOf(input.availabilityState.name),
            coolbluesChoiceInformationTitle = input.coolbluesChoiceInformationTitle.orEmpty(),
            listPriceIncVat = input.listPriceExVat?.let { generateOldPrice(it.toString()) }
                ?: SpannedString(""),
            nextDayDelivery = input.nextDayDelivery,
            productImage = input.productImage,
            productName = input.productName,
            promoIconType = input.promoIcon?.let {promoIconUiMapper.map(it)},
            reviewAverage = input.reviewInformation.reviewSummary.reviewAverage,
            reviewCount = "${input.reviewInformation.reviewSummary.reviewCount} reviews",
            salesPriceIncVat = input.salesPriceIncVat.toString(),
            uSPs = generateUSPs(input.uSPs)
        )
    }

    private fun generateOldPrice(price: String): SpannedString {
        return buildSpannedString {
            inSpans(StrikethroughSpan()) {
                append(price)
            }
        }
    }

    private fun generateUSPs(list: List<String>): SpannedString {
        return buildSpannedString {
            list.forEach {
                append("\u25CF $it \n")
            }
        }
    }
}
