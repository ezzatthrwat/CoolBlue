package com.example.shopping.presentation.search.viewstate

import com.example.core.viewstate.ViewAction

sealed class SearchViewAction : ViewAction {

    data class SearchAction(val searchQuery: String) : SearchViewAction()

    object LoadNextPage : SearchViewAction()

    object AddToCartAction : SearchViewAction()

    object SendMailAction : SearchViewAction()

}