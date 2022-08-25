package com.example.shopping.remote.datasource

import com.example.shopping.test.factory.ProductsResultFactory
import com.example.shopping.data.model.ProductsResultEntity
import com.example.shopping.remote.api.ProductsApi
import com.example.shopping.remote.mapper.ProductsEntityMapper
import com.example.shopping.remote.model.ProductsResultRemote
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProductsRemoteDataSourceImplTest {

    private val api = mock<ProductsApi>()
    private val productsEntityMapper = mock<ProductsEntityMapper>()

    private val productsRemoteDataSourceImpl = ProductsRemoteDataSourceImpl(
        api = api,
        ProductsEntityMapper = productsEntityMapper
    )

    @Test
    fun `getProducts call the api with correct params`() {
        val page = 1
        val searchQuery = "search"

        stubGetProducts(Single.never())

        productsRemoteDataSourceImpl.getProducts(
            query = searchQuery, page = page
        )

        verify(api).getProducts(
            query = searchQuery, page = page
        )
    }

    @Test
    fun `getProducts return data and complete`() {

        val productResultRemote = ProductsResultFactory.makeProductsResultRemote()
        val productResultEntity = ProductsResultFactory.makeProductsResultEntity()
        val page = 1
        val searchQuery = "search"

        stubGetProducts(Single.just(productResultRemote))
        stubProductsEntityMapper(productResultEntity)

        val testObservable = productsRemoteDataSourceImpl.getProducts(
            query = searchQuery, page = page
        ).test()

        testObservable.assertResult(productResultEntity)
    }

    private fun stubProductsEntityMapper(productResultEntity: ProductsResultEntity) {
        whenever(productsEntityMapper.map(any()))
            .thenReturn(productResultEntity)
    }

    private fun stubGetProducts(single: Single<ProductsResultRemote>) {
        whenever(api.getProducts(any(), any()))
            .thenReturn(single)
    }
}
