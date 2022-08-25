package com.example.shopping.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.shopping.data.model.AvailabilityStateEntity
import com.example.shopping.data.model.ProductEntity
import com.example.shopping.remote.model.ProductRemote
import javax.inject.Inject

class ProductEntityMapper @Inject constructor(
    private val promoIconEntityMapper: PromoIconEntityMapper,
    private val reviewInformationEntityMapper: ReviewInformationEntityMapper
) : Mapper<ProductRemote, ProductEntity> {

    override fun map(input: ProductRemote): ProductEntity {

        assertEssentialParams(input)

        return ProductEntity(
            productId = input.productId!!,
            availabilityState = AvailabilityStateEntity.valueOf(input.availabilityState().name),
            coolbluesChoiceInformationTitle = input.coolbluesChoiceInformationTitle,
            listPriceExVat = input.listPriceExVat,
            listPriceIncVat = input.listPriceIncVat,
            nextDayDelivery = input.nextDayDelivery!!,
            productImage = input.productImage!!,
            productName = input.productName!!,
            promoIconEntity = promoIconEntityMapper.map(input.promoIconRemote),
            reviewInformationEntity = reviewInformationEntityMapper.map(input.reviewInformationRemote!!),
            salesPriceIncVat = input.salesPriceIncVat!!,
            uSPs = input.uSPs!!
        )
    }

    private fun assertEssentialParams(remote: ProductRemote) {

        val essentialParams = listOf(
            EssentialParam(remote.productId, "productId"),
            EssentialParam(remote.availabilityState, "availabilityState"),
            EssentialParam(remote.nextDayDelivery, "nextDayDelivery"),
            EssentialParam(remote.productImage, "productImage"),
            EssentialParam(remote.productName, "productName"),
            EssentialParam(remote.salesPriceIncVat, "salesPriceIncVat"),
            EssentialParam(remote.uSPs, "uSPs"),
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}
