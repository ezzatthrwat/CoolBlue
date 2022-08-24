package com.example.shopping.presentation.search.viewstate

import com.example.core.viewstate.ViewState
import com.example.shopping.presentation.search.model.ProductUiModel

sealed class SearchViewState : ViewState {

    object Loading : SearchViewState()

    data class Success(
        val products: List<ProductUiModel>,
        val loadingNextPage: Boolean,
        val empty: Boolean,
    ) : SearchViewState()

    object Error : SearchViewState()
}