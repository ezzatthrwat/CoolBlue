package com.example.core.adapter.pagingloader

sealed class PagingLoaderState {

    object Init : PagingLoaderState()

    object Loading : PagingLoaderState()

    object Done : PagingLoaderState()
}
