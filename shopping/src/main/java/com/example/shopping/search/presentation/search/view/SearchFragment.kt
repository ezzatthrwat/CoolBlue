package com.example.shopping.search.presentation.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.base.view.BaseFragment
import com.example.shopping.R
import com.example.shopping.databinding.SearchFragmentBinding
import com.example.shopping.search.presentation.search.viewmodel.SearchViewModel
import com.example.shopping.search.presentation.search.viewstate.SearchCoordinatorEvent
import com.example.shopping.search.presentation.search.viewstate.SearchViewAction
import com.example.shopping.search.presentation.search.viewstate.SearchViewEvent
import com.example.shopping.search.presentation.search.viewstate.SearchViewState
import dagger.hilt.android.AndroidEntryPoint

class SearchFragment : BaseFragment<
        SearchViewState,
        SearchViewEvent,
        SearchViewAction,
        SearchCoordinatorEvent,
        SearchViewModel,
        SearchFragmentBinding>() {

    override val viewModel: SearchViewModel by viewModels()

    override val fragmentTheme: Int = R.style.Theme_CoolBlueTask_shopping

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SearchFragmentBinding {
      return SearchFragmentBinding.inflate(inflater, container, false)
    }

    override fun renderViewState(viewState: SearchViewState) {

    }

    override fun renderViewEvent(viewEvent: SearchViewEvent) {

    }

    override fun coordinatorEvent(coordinatorEvent: SearchCoordinatorEvent) {

    }
}