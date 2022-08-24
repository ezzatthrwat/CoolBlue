package com.example.shopping.presentation.search.viewstate

import com.example.core.viewstate.ViewEvent

sealed class SearchViewEvent : ViewEvent {

    object ProductsNextPageError : SearchViewEvent()

    data class ShowProductIdToast(val productId: Long) : SearchViewEvent()

    object ShowSendEmailToast : SearchViewEvent()

    object NoInternet : SearchViewEvent()

}