package com.example.core.adapter.pagingloader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.example.core.R
import com.example.core.databinding.AdapterPagingLoaderBinding


class PagingLoaderAdapter(@ColorRes private val tint: Int = R.color.blue_light) :
    BasePagingLoaderAdapter<AdapterPagingLoaderBinding>(tint) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AdapterPagingLoaderBinding {
        return AdapterPagingLoaderBinding.inflate(inflater, parent, false)
    }

    override fun onBindItem(binding: AdapterPagingLoaderBinding, item: Any?) {
        bindProgressBar(binding.progressBar)
    }
}
