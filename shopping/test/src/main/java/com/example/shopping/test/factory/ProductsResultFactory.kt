package com.example.shopping.test.factory

import com.example.core_testing.factory.DataFactory.randomInt
import com.example.shopping.data.model.ProductsResultEntity
import com.example.shopping.domain.model.ProductsResult
import com.example.shopping.remote.model.ProductsResultRemote

object ProductsResultFactory {

    fun makeProductsResult() = ProductsResult(
        currentPage = randomInt(),
        pageCount = randomInt(),
        products = listOf(ProductFactory.makeProduct()),
    )

    fun makeProductsResultEntity() = ProductsResultEntity(
        currentPage = randomInt(),
        pageCount = randomInt(),
        productEntities = listOf(ProductFactory.makeProductEntity()),
    )

    fun makeProductsResultRemote() = ProductsResultRemote(
        currentPage = randomInt(),
        pageCount = randomInt(),
        productRemotes = listOf(ProductFactory.makeProductRemote()),
        pageSize = 24,
        totalResults = 70
    )
}
