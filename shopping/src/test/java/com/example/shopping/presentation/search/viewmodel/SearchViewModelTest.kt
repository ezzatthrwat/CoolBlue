package com.example.shopping.presentation.search.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.util.NetworkChecker
import com.example.core_testing.scheduler.TestSchedulerProvider
import com.example.shopping.domain.usecase.GetProductsUseCase
import com.example.shopping.presentation.search.mapper.ProductUiMapper
import com.example.shopping.presentation.search.model.ProductUiModel
import com.example.shopping.presentation.search.viewstate.SearchCoordinatorEvent
import com.example.shopping.presentation.search.viewstate.SearchViewAction
import com.example.shopping.presentation.search.viewstate.SearchViewEvent
import com.example.shopping.presentation.search.viewstate.SearchViewState
import com.example.shopping.test.factory.ProductFactory
import com.example.shopping.test.factory.ProductsResultFactory
import io.reactivex.rxjava3.core.Observable
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getProductsUseCase = mock<GetProductsUseCase>()
    private val productUiMapper = mock<ProductUiMapper>()
    private val networkChecker = mock<NetworkChecker>()
    private val schedulerProvider = TestSchedulerProvider()

    private val viewModel: SearchViewModel by lazy {
        SearchViewModel(
            getProductsUseCase = getProductsUseCase,
            productUiMapper = productUiMapper,
            schedulerProvider = schedulerProvider,
            networkChecker = networkChecker
        )
    }

    @Test
    fun `test viewModel will init with InitState`() {
        assertEquals(SearchViewState.InitState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post SearchAction won't call useCase if internet is down`() {
        val searchQuery = "Search"

        stubNetworkChecker(isConnected = false)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        verifyNoInteractions(getProductsUseCase)
    }

    @Test
    fun `test viewModel post SearchAction send NoInternet viewEvent call useCase if internet is down`() {
        val searchQuery = "Search"

        stubNetworkChecker(isConnected = false)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        assertEquals(SearchViewEvent.NoInternet, viewModel.viewEvent.value?.peekContent())
    }

    @Test
    fun `test viewModel post SearchAction will call useCase if internet is connected`() {
        val searchQuery = "Search"
        stubNetworkChecker(isConnected = true)

        stubGetProductUseCase(Observable.never())

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        val params = GetProductsUseCase.Params(searchQuery)

        verify(getProductsUseCase).execute(params)
    }

    @Test
    fun `test viewModel post SearchAction return Loading viewState when the search query length more than 2`() {
        val searchQuery = "Search"
        val expectedViewState = SearchViewState.Loading

        stubNetworkChecker(isConnected = true)
        stubGetProductUseCase(Observable.never())

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        assertEquals(expectedViewState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post SearchAction return ClearList viewState when the search query length less than or equal 2`() {
        val searchQuery = "Se"
        val expectedViewState = SearchViewState.ClearList

        stubNetworkChecker(isConnected = true)
        stubGetProductUseCase(Observable.never())

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        assertEquals(expectedViewState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post SearchAction return success viewState`() {
        val searchQuery = "Search"
        val productUi = ProductFactory.makeProductUiModel()
        val productUiList = listOf(productUi)
        val productsResult = ProductsResultFactory.makeProductsResult()
        val result = GetProductsUseCase.Result.Success(productsResult)
        val expectedViewState = SearchViewState.Success(
            products = productUiList,
            loadingNextPage = false,
            empty = productUiList.isEmpty(),
        )

        stubNetworkChecker(isConnected = true)
        stubGetProductUseCase(Observable.just(result))
        stubProductUiMapper(productUiList)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        assertEquals(expectedViewState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post SearchAction return Error viewState if error`() {
        val searchQuery = "Search"
        val error = Throwable()
        val result = GetProductsUseCase.Result.Error(error)
        val expectedViewState = SearchViewState.Error

        stubNetworkChecker(isConnected = true)
        stubGetProductUseCase(Observable.just(result))

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        assertEquals(expectedViewState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post LoadNextPage call useCase nextPage`() {
        val searchQuery = "Search"
        val productUi = ProductFactory.makeProductUiModel()
        val productUiList = listOf(productUi)
        val productsResult = ProductsResultFactory.makeProductsResult()
        val result = GetProductsUseCase.Result.Success(productsResult)
        stubNetworkChecker(isConnected = true)

        stubGetProductUseCase(Observable.just(result))
        stubProductUiMapper(productUiList)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        stubHasMore(true)

        viewModel.postAction(SearchViewAction.LoadNextPage)

        verify(getProductsUseCase).nextPage()
    }

    @Test
    fun `test viewModel post LoadNextPage won't call useCase when hasMore equal false`() {

        stubNetworkChecker(isConnected = true)

        stubGetProductUseCase(Observable.never())

        stubHasMore(false)

        viewModel.postAction(SearchViewAction.LoadNextPage)

        verifyNoInteractions(getProductsUseCase)
    }

    @Test
    fun `test viewModel post LoadNextPage send NoInternet viewEvent when internet down`() {
        val searchQuery = "Search"
        val productUi = ProductFactory.makeProductUiModel()
        val productUiList = listOf(productUi)
        val productsResult = ProductsResultFactory.makeProductsResult()
        val result = GetProductsUseCase.Result.Success(productsResult)
        val expectedViewEvent = SearchViewEvent.NoInternet

        stubNetworkChecker(isConnected = false)

        stubGetProductUseCase(Observable.just(result))
        stubProductUiMapper(productUiList)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        stubHasMore(true)

        viewModel.postAction(SearchViewAction.LoadNextPage)

        assertEquals(expectedViewEvent, viewModel.viewEvent.value?.peekContent())
    }


    @Test
    fun `test viewModel post LoadNextPage send Success viewState with loadingNextPage equal true`() {
        val searchQuery = "Search"
        val productUi = ProductFactory.makeProductUiModel()
        val productUiList = listOf(productUi)
        val productsResult = ProductsResultFactory.makeProductsResult()
        val result = GetProductsUseCase.Result.Success(productsResult)
        val expectedViewState = SearchViewState.Success(
            products = productUiList,
            loadingNextPage = true,
            empty = productUiList.isEmpty(),
        )

        stubNetworkChecker(isConnected = true)

        stubGetProductUseCase(Observable.just(result))
        stubProductUiMapper(productUiList)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        stubHasMore(true)

        viewModel.postAction(SearchViewAction.LoadNextPage)

        assertEquals(expectedViewState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post LoadNextPage send ProductsNextPageError viewEvent when error`() {
        val searchQuery = "Search"
        val productUi = ProductFactory.makeProductUiModel()
        val productUiList = listOf(productUi)
        val productsResult = ProductsResultFactory.makeProductsResult()
        val error = Throwable()
        val successResult = GetProductsUseCase.Result.Success(productsResult)
        val errorResult = GetProductsUseCase.Result.Error(error)
        val expectedViewState = SearchViewState.Success(
            products = productUiList,
            loadingNextPage = true,
            empty = productUiList.isEmpty(),
        )

        stubNetworkChecker(isConnected = true)

        stubGetProductUseCase(Observable.just(successResult))
        stubProductUiMapper(productUiList)

        viewModel.postAction(SearchViewAction.SearchAction(searchQuery))

        stubGetProductUseCase(Observable.just(errorResult))

        stubHasMore(true)

        viewModel.postAction(SearchViewAction.LoadNextPage)

        assertEquals(expectedViewState, viewModel.currentViewState())
    }

    @Test
    fun `test viewModel post AddToCartAction send ShowProductIdToast viewEvent`() {
        val productId = 1L
        val expectedViewEvent = SearchViewEvent.ShowProductIdToast(productId)

        viewModel.postAction(SearchViewAction.AddToCartAction(productId))

        assertEquals(expectedViewEvent, viewModel.viewEvent.value?.peekContent())
    }

    @Test
    fun `test viewModel post SendMailAction send ShowSendEmailToast viewEvent`() {
        val expectedViewEvent = SearchViewEvent.ShowSendEmailToast

        viewModel.postAction(SearchViewAction.SendMailAction)

        assertEquals(expectedViewEvent, viewModel.viewEvent.value?.peekContent())
    }

    @Test
    fun `test viewModel post OpenProductDetails send ShowProductIdToast CoordinatorEvent`() {
        val productId = 1L
        val expectedCoordinatorEvent = SearchCoordinatorEvent.OpenDetails(productId)

        viewModel.postAction(SearchViewAction.OpenProductDetails(productId))

        assertEquals(expectedCoordinatorEvent, viewModel.coordinatorEvent.value?.peekContent())
    }

    private fun stubNetworkChecker(isConnected: Boolean) {
        whenever(networkChecker.isConnectedToInternet()).thenReturn(isConnected)
    }

    private fun stubGetProductUseCase(observable: Observable<GetProductsUseCase.Result>) {
        whenever(getProductsUseCase.execute(any())).thenReturn(observable)
    }

    private fun stubProductUiMapper(productUiList: List<ProductUiModel>) {
        whenever(productUiMapper.mapList(any()))
            .thenReturn(productUiList)
    }

    private fun stubHasMore(hasMore: Boolean) {
        whenever(getProductsUseCase.hasMore).thenReturn(hasMore)
    }
}
