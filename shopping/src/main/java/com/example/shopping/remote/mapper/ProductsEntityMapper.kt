package com.example.shopping.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ProductsResultEntity
import com.example.shopping.remote.model.ProductsResultRemote
import javax.inject.Inject

class ProductsEntityMapper @Inject constructor(
    private val productEntityMapper: ProductEntityMapper
) : Mapper<ProductsResultRemote, ProductsResultEntity> {

    override fun map(input: ProductsResultRemote): ProductsResultEntity {

        assertEssentialParams(input)

        return ProductsResultEntity(
            currentPage = input.currentPage!!,
            pageCount = input.pageCount!!,
            productEntities = productEntityMapper.mapList(input.productRemotes!!),
        )
    }

    private fun assertEssentialParams(remote: ProductsResultRemote) {

        val essentialParams = listOf(
            EssentialParam(remote.currentPage, "currentPage"),
            EssentialParam(remote.pageCount, "pageCount"),
            EssentialParam(remote.productRemotes, "products"),
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}
