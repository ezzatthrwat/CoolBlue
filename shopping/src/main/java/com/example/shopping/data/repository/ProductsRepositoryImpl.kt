package com.example.shopping.data.repository

import com.example.shopping.data.datasource.ProductsRemoteDataSource
import com.example.shopping.data.mapper.ProductsResultMapper
import com.example.shopping.domain.model.ProductsResult
import com.example.shopping.domain.repository.ProductsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductsRemoteDataSource,
    private val productsResultMapper: ProductsResultMapper
) : ProductsRepository {

    override fun getProducts(query: String, page: Int): Single<ProductsResult> {
        return productsRemoteDataSource.getProducts(query, page)
            .map { productsResultMapper.map(it) }
    }

}