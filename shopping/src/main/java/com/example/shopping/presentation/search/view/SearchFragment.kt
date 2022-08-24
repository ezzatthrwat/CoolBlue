package com.example.shopping.presentation.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.example.core.adapter.pagingloader.PagingLoaderAdapter
import com.example.core.base.view.BaseFragment
import com.example.core.extension.onChange
import com.example.core.extension.onEndReached
import com.example.core.extension.shortSnackbar
import com.example.core.extension.showToast
import com.example.shopping.R
import com.example.shopping.databinding.FragmentSearchBinding
import com.example.shopping.presentation.search.view.adapter.ProductsAdapter
import com.example.shopping.presentation.search.viewmodel.SearchViewModel
import com.example.shopping.presentation.search.viewstate.SearchCoordinatorEvent
import com.example.shopping.presentation.search.viewstate.SearchViewAction
import com.example.shopping.presentation.search.viewstate.SearchViewEvent
import com.example.shopping.presentation.search.viewstate.SearchViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<
        SearchViewState,
        SearchViewEvent,
        SearchViewAction,
        SearchCoordinatorEvent,
        SearchViewModel,
        FragmentSearchBinding>() {

    override val viewModel: SearchViewModel by viewModels()

    override val fragmentTheme: Int = R.style.Theme_CoolBlueTask_shopping


    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var productPagingLoader: PagingLoaderAdapter
    private lateinit var productsConcatAdapter: ConcatAdapter

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductsRecyclerView()

        binding.searchEditText.onChange { searchText ->
            postAction(SearchViewAction.SearchAction(searchText))
        }
    }

    private fun setupProductsRecyclerView() {
        setupAdapters()

        binding.productsRecyclerView.onEndReached { postAction(SearchViewAction.LoadNextPage) }
        binding.productsRecyclerView.setHasFixedSize(true)
        binding.productsRecyclerView.adapter = productsConcatAdapter
    }

    private fun setupAdapters() {
        productsAdapter = ProductsAdapter(
            onProductItemClicked = { productId ->
                postAction(
                    SearchViewAction.OpenProductDetails(
                        productId = productId
                    )
                )
            },
            onAddToCartClicked = { productId ->
                postAction(
                    SearchViewAction.AddToCartAction(
                        productId = productId
                    )
                )
            },
            onMailClicked = { postAction(SearchViewAction.SendMailAction) }
        )
        productPagingLoader = PagingLoaderAdapter()
        productsConcatAdapter = ConcatAdapter(productsAdapter, productPagingLoader)
    }

    override fun renderViewState(viewState: SearchViewState) {
        binding.productsProgressBar.isVisible = viewState is SearchViewState.Loading

        when (viewState) {
            is SearchViewState.InitState -> {}
            is SearchViewState.Loading -> {}
            is SearchViewState.Success -> {
                productsAdapter.submitList(viewState.products)
                productPagingLoader.isLoading = viewState.loadingNextPage
            }
            is SearchViewState.Error ->
                shortSnackbar(binding.searchContainer, R.string.server_error, requireContext())
        }
    }

    override fun renderViewEvent(viewEvent: SearchViewEvent) {
        when (viewEvent) {
            is SearchViewEvent.ProductsNextPageError ->
                shortSnackbar(binding.searchContainer, R.string.server_error, requireContext())
            is SearchViewEvent.ShowProductIdToast ->
                showToast(requireContext(), "${viewEvent.productId}")
            is SearchViewEvent.ShowSendEmailToast ->
                showToast(requireContext(), "Send Email")
            is SearchViewEvent.NoInternet ->
                shortSnackbar(binding.searchContainer, R.string.internet_error, requireContext())
        }
    }

    override fun coordinatorEvent(coordinatorEvent: SearchCoordinatorEvent) {
        when(coordinatorEvent) {
            is SearchCoordinatorEvent.OpenDetails ->
                showToast(requireContext(), "Open ${coordinatorEvent.productId} Details")
        }
    }
}