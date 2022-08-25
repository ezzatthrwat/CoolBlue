package com.example.shopping.data.datasource

import com.example.shopping.data.model.ProductsResultEntity
import io.reactivex.rxjava3.core.Single

interface ProductsRemoteDataSource {

    fun getProducts(
        query: String,
        page: Int
    ): Single<ProductsResultEntity>
}
