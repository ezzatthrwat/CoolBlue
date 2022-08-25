package com.example.shopping.data.mapper

import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ProductEntity
import com.example.shopping.domain.model.AvailabilityState
import com.example.shopping.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val promoIconMapper: PromoIconMapper,
    private val reviewInformationMapper: ReviewInformationMapper
) : Mapper<ProductEntity, Product> {

    override fun map(input: ProductEntity): Product {

        return Product(
            productId = input.productId,
            availabilityState = AvailabilityState.valueOf(input.availabilityState.name),
            coolbluesChoiceInformationTitle = input.coolbluesChoiceInformationTitle,
            listPriceExVat = input.listPriceExVat,
            listPriceIncVat = input.listPriceIncVat,
            nextDayDelivery = input.nextDayDelivery,
            productImage = input.productImage,
            productName = input.productName,
            promoIcon = promoIconMapper.map(input.promoIconEntity),
            reviewInformation = reviewInformationMapper.map(input.reviewInformationEntity),
            salesPriceIncVat = input.salesPriceIncVat,
            uSPs = input.uSPs
        )
    }
}
