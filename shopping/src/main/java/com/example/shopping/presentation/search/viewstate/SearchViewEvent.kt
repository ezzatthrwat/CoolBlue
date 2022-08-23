package com.example.shopping.presentation.search.viewstate

import com.example.core.viewstate.ViewEvent

sealed class SearchViewEvent : ViewEvent {

    object ProductsNextPageError : SearchViewEvent()

    object NoInternet : SearchViewEvent()

}