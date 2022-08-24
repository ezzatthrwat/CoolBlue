package com.example.shopping.presentation.search.viewmodel

import com.example.core.base.viewmodel.BaseViewModel
import com.example.core.util.NetworkChecker
import com.example.shopping.domain.usecase.GetProductsUseCase
import com.example.shopping.presentation.search.mapper.ProductUiMapper
import com.example.shopping.presentation.search.viewstate.SearchCoordinatorEvent
import com.example.shopping.presentation.search.viewstate.SearchViewAction
import com.example.shopping.presentation.search.viewstate.SearchViewEvent
import com.example.shopping.presentation.search.viewstate.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val productUiMapper: ProductUiMapper,
    private val networkChecker: NetworkChecker
) : BaseViewModel<SearchViewState, SearchViewEvent, SearchViewAction, SearchCoordinatorEvent>() {

    private val loadProduct = PublishSubject.create<GetProductsUseCase.Params>()

    override val initViewState: SearchViewState =
        SearchViewState.InitState.also { updateViewState(it) }

    init {
        initProductsSearchObservable()
    }

    override fun postAction(action: SearchViewAction) {
        when (action) {
            is SearchViewAction.SearchAction -> getProducts(action.searchQuery)
            is SearchViewAction.LoadNextPage -> loadNextPage()
            is SearchViewAction.AddToCartAction -> {
                updateViewEvent(SearchViewEvent.ShowProductIdToast(action.productId))
            }
            is SearchViewAction.SendMailAction -> {
                updateViewEvent(SearchViewEvent.ShowSendEmailToast)
            }
            is SearchViewAction.OpenProductDetails -> {
                sendCoordinatorEvent(SearchCoordinatorEvent.OpenDetails(action.productId))
            }
        }
    }

    private fun getProducts(searchQuery: String) {
        val minCharacterLengthToStartSearch = 2
        safeRequest {
            if (searchQuery.isEmpty() || searchQuery.length <= minCharacterLengthToStartSearch) {
                val success = (currentViewState() as? SearchViewState.Success)
                if (success != null) {
                    updateViewState(success.copy(products = listOf()))
                }
            } else {
                updateViewState(SearchViewState.Loading)
                loadProduct.onNext(GetProductsUseCase.Params(searchQuery))
            }
        }
    }

    private fun initProductsSearchObservable() {
        val newSearchThreshold = 400L
        loadProduct
            .debounce(newSearchThreshold, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { params -> getProductsUseCase.execute(params) }
            .subscribe(
                { result -> handleGetProductsRequestResult(result) },
                { error -> handleProductsLoadingError() }
            )
    }

    private fun loadNextPage() {
        val success = currentViewState() as? SearchViewState.Success
        val isLoadNext = success?.loadingNextPage ?: false
        if (success != null && !isLoadNext && getProductsUseCase.hasMore) {
            safeRequest {
                updateViewState(success.copy(loadingNextPage = true))
                getProductsUseCase.nextPage()
            }
        }
    }

    private fun handleGetProductsRequestResult(result: GetProductsUseCase.Result) {
        when (result) {
            is GetProductsUseCase.Result.Success -> {
                val successViewState = currentViewState() as? SearchViewState.Success
                updateViewState(
                    successViewState?.copy(
                        products = productUiMapper.mapList(result.data.products),
                        loadingNextPage = false,
                        empty = result.data.products.isEmpty()
                    ) ?: run {
                        SearchViewState.Success(
                            products = productUiMapper.mapList(result.data.products),
                            loadingNextPage = false,
                            empty = result.data.products.isEmpty()
                        )
                    }
                )
            }
            is GetProductsUseCase.Result.Error -> {
                handleProductsLoadingError()
            }
        }
    }

    private fun handleProductsLoadingError() {
        val success = currentViewState() as? SearchViewState.Success
        if (success != null && !success.empty) {
            updateViewEvent(SearchViewEvent.ProductsNextPageError)
            updateViewState(
                success.copy(
                    loadingNextPage = false
                )
            )
        } else {
            updateViewState(
                SearchViewState.Error
            )
        }
    }

    private fun safeRequest(request: () -> Unit) {
        if (networkChecker.isConnectedToInternet()) {
            request.invoke()
        } else {
            updateViewEvent(SearchViewEvent.NoInternet)
        }
    }
}
