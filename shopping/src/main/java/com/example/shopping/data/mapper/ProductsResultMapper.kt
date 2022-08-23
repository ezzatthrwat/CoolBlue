package com.example.shopping.data.mapper

import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ProductsResultEntity
import com.example.shopping.domain.model.ProductsResult
import javax.inject.Inject

class ProductsResultMapper @Inject constructor(
    private val productMapper: ProductMapper
) : Mapper<ProductsResultEntity, ProductsResult> {

    override fun map(input: ProductsResultEntity) : ProductsResult {
        return ProductsResult (
            currentPage = input.currentPage,
            pageCount = input.pageCount,
            products = productMapper.mapList(input.productEntities),
        )
    }
}
