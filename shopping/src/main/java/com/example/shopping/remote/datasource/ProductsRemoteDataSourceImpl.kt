package com.example.shopping.remote.datasource

import com.example.shopping.data.datasource.ProductsRemoteDataSource
import com.example.shopping.data.model.ProductsResultEntity
import com.example.shopping.remote.api.ProductsApi
import com.example.shopping.remote.mapper.ProductsEntityMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductsRemoteDataSourceImpl @Inject constructor(
    private val api: ProductsApi,
    private val ProductsEntityMapper: ProductsEntityMapper
) : ProductsRemoteDataSource {

    override fun getProducts(query: String, page: Int): Single<ProductsResultEntity> {
        return api.getProducts(query, page).map { ProductsEntityMapper.map(it) }
    }
}
