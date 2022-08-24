package com.example.shopping.data.repository

import com.example.shopping.test.factory.ProductsResultFactory
import com.example.shopping.data.datasource.ProductsRemoteDataSource
import com.example.shopping.data.mapper.ProductsResultMapper
import com.example.shopping.data.model.ProductsResultEntity
import com.example.shopping.domain.model.ProductsResult
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProductsRepositoryImplTest {

    private val productsRemoteDataSource = mock<ProductsRemoteDataSource>()
    private val productsResultMapper = mock<ProductsResultMapper>()

    private val productsRepositoryImpl = ProductsRepositoryImpl(
        productsRemoteDataSource = productsRemoteDataSource,
        productsResultMapper = productsResultMapper
    )

    @Test
    fun `getProducts call datasource with correct param`() {
        val page = 1
        val searchQuery = "search"

        stubGetProducts(Single.never())

        productsRepositoryImpl.getProducts(
            query = searchQuery, page = page
        )

        verify(productsRemoteDataSource).getProducts(
            query = searchQuery, page = page
        )
    }


    @Test
    fun `getProducts return data and complete`() {
        val entity = ProductsResultFactory.makeProductsResultEntity()
        val domain = ProductsResultFactory.makeProductsResult()
        val page = 1
        val searchQuery = "search"

        stubGetProducts(Single.just(entity))
        stubProductsResultMapper(domain)

        val testObservable = productsRepositoryImpl.getProducts(
            query = searchQuery, page = page
        ).test()

        testObservable.assertResult(domain)
    }


    private fun stubProductsResultMapper(productResult: ProductsResult) {
        whenever(productsResultMapper.map(any()))
            .thenReturn(productResult)
    }

    private fun stubGetProducts(single: Single<ProductsResultEntity>) {
        whenever(productsRemoteDataSource.getProducts(any(), any()))
            .thenReturn(single)
    }
}
