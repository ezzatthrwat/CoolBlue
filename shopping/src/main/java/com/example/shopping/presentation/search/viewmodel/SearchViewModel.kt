package com.example.shopping.presentation.search.viewmodel

import com.example.core.base.viewmodel.BaseViewModel
import com.example.core.util.NetworkChecker
import com.example.shopping.presentation.search.viewstate.SearchCoordinatorEvent
import com.example.shopping.presentation.search.viewstate.SearchViewAction
import com.example.shopping.presentation.search.viewstate.SearchViewEvent
import com.example.shopping.presentation.search.viewstate.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val networkChecker: NetworkChecker) :
    BaseViewModel<SearchViewState, SearchViewEvent, SearchViewAction, SearchCoordinatorEvent> () {

    override val initViewState: SearchViewState = SearchViewState.Init

    override fun postAction(action: SearchViewAction) {
    }
}