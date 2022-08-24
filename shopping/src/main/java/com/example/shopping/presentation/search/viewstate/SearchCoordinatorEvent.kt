package com.example.shopping.presentation.search.viewstate

import com.example.core.navigation.CoordinatorEvent

sealed class SearchCoordinatorEvent : CoordinatorEvent {

   data class OpenDetails(val productId: Long) :SearchCoordinatorEvent()
}