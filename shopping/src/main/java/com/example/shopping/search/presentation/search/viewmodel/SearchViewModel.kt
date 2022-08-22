package com.example.shopping.search.presentation.search.viewmodel

import com.example.core.base.viewmodel.BaseViewModel
import com.example.shopping.search.presentation.search.viewstate.SearchCoordinatorEvent
import com.example.shopping.search.presentation.search.viewstate.SearchViewAction
import com.example.shopping.search.presentation.search.viewstate.SearchViewEvent
import com.example.shopping.search.presentation.search.viewstate.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseViewModel<SearchViewState, SearchViewEvent, SearchViewAction, SearchCoordinatorEvent> () {

    override val initViewState: SearchViewState = SearchViewState.Init

    override fun postAction(action: SearchViewAction) {

    }
}