package com.example.shopping.search.presentation.search.viewstate

import com.example.core.viewstate.ViewState

sealed class SearchViewState : ViewState {

    object Init: SearchViewState()
}