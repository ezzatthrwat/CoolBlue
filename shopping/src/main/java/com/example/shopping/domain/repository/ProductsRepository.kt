package com.example.shopping.domain.repository

import com.example.shopping.domain.model.ProductsResult
import io.reactivex.rxjava3.core.Single

interface ProductsRepository {

    fun getProducts(
        query: String,
        page: Int
    ) : Single<ProductsResult>

}