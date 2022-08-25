package com.example.shopping.domain.usecase

import com.example.core.exception.ParamMissingException
import com.example.core_testing.scheduler.TestSchedulerProvider
import com.example.shopping.domain.model.Product
import com.example.shopping.domain.model.ProductsResult
import com.example.shopping.domain.repository.ProductsRepository
import com.example.shopping.test.factory.ProductFactory
import com.example.shopping.test.factory.ProductsResultFactory
import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class GetProductsUseCaseTest {

    private val repository = mock<ProductsRepository>()
    private val schedulerProvider = TestSchedulerProvider()

    private val getProductsUseCase = GetProductsUseCase(
        repository = repository,
        schedulerProvider = schedulerProvider
    )

    @Test(expected = ParamMissingException::class)
    fun `build use case observable with out params throw exception`() {
        val params = null
        getProductsUseCase.execute(params)
        verifyNoInteractions(repository)
    }

    @Test
    fun `build use case observable calls repository starting from page 1 with the correct params`() {
        val page = 1
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)

        getProductsUseCase.execute(params).test()

        verify(repository).getProducts(searchQuery, page)
    }

    @Test
    fun `build use case observable update use case result list in success`() {
        val productResult = ProductsResultFactory.makeProductsResult()
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery = searchQuery)

        stubGetProduct(Single.just(productResult))

        getProductsUseCase.execute(params).test()

        assertEquals(productResult.products, getProductsUseCase.currentList())
    }

    @Test
    fun `build use case observable clears previous list and replace it with the new list with each success`() {
        // First call
        val productResult1 = ProductsResultFactory.makeProductsResult()
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)

        stubGetProduct(Single.just(productResult1))

        getProductsUseCase.execute(params).test()
        assertEquals(productResult1.products, getProductsUseCase.currentList())

        // second call
        val productResult2 = ProductsResultFactory.makeProductsResult()

        stubGetProduct(Single.just(productResult2))

        getProductsUseCase.execute(params).test()
        assertEquals(productResult2.products, getProductsUseCase.currentList())
    }

    @Test
    fun `build use case observable returns success result if load page returns data`() {
        val productResult = ProductsResultFactory.makeProductsResult()
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery = searchQuery)

        stubGetProduct(Single.just(productResult))

        val testObserver = getProductsUseCase.execute(params).test()

        testObserver.assertValue(GetProductsUseCase.Result.Success(productResult))
    }

    @Test
    fun `build use case observable add new data from next pages to use case results list`() {
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)
        val productResult1 = ProductsResultFactory.makeProductsResult()
        val productResult2 = ProductsResultFactory.makeProductsResult().copy(
            products = listOf(ProductFactory.makeProduct().copy(productId = 23L))
        )
        val expectedResult = mutableListOf<Product>().apply {
            addAll(productResult1.products)
            addAll(productResult2.products)
        }

        stubGetProduct(Single.just(productResult1))
        getProductsUseCase.execute(params).test()
        assertEquals(productResult1.products, getProductsUseCase.currentList())

        stubGetProduct(Single.just(productResult2))
        getProductsUseCase.nextPage()
        assertEquals(expectedResult, getProductsUseCase.currentList())
    }

    @Test
    fun `build use case observable returns error result if load page failed and don't break the stream`() {
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)
        val throwable = Throwable()

        stubGetProduct(Single.error(throwable))

        val testObservable = getProductsUseCase.execute(params).test()
        testObservable.assertValue(GetProductsUseCase.Result.Error(throwable = throwable))
            .assertNoErrors()
    }

    @Test
    fun `has more is true when the current page is less than returned pages count`() {
        val currentPage = 1
        val pageCount = 3
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)
        val productResult = ProductsResultFactory.makeProductsResult().copy(
            currentPage = currentPage, pageCount = pageCount
        )

        stubGetProduct(Single.just(productResult))

        getProductsUseCase.execute(params).test()

        assertTrue(getProductsUseCase.hasMore)
    }

    @Test
    fun `has more is false when the current page is equal to returned pages count`() {
        val currentPage = 3
        val pageCount = 3
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)
        val productResult = ProductsResultFactory.makeProductsResult().copy(
            currentPage = currentPage, pageCount = pageCount
        )

        stubGetProduct(Single.just(productResult))

        getProductsUseCase.execute(params).test()

        assertFalse(getProductsUseCase.hasMore)
    }

    @Test
    fun `next page calls load page again with the new page number to get the next page`() {
        val page = 1
        val searchQuery = "search"
        val params = GetProductsUseCase.Params(searchQuery)
        val productResult = ProductsResultFactory.makeProductsResult()

        stubGetProduct(Single.just(productResult))

        getProductsUseCase.execute(params).test()
        verify(repository).getProducts(searchQuery, page)

        getProductsUseCase.nextPage()
        verify(repository).getProducts(searchQuery, page + 1)
    }

    private fun stubGetProduct(single: Single<ProductsResult>) {
        whenever(repository.getProducts(any(), any()))
            .thenReturn(single)
    }
}
