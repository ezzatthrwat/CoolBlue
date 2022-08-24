package com.example.shopping.presentation.search.viewstate

import com.example.core.viewstate.ViewAction

sealed class SearchViewAction : ViewAction {

    data class SearchAction(val searchQuery: String) : SearchViewAction()

    object LoadNextPage : SearchViewAction()

    data class AddToCartAction(val productId: Long) : SearchViewAction()

    data class OpenProductDetails(val productId: Long) : SearchViewAction()

    object SendMailAction : SearchViewAction()

}