package com.example.core.adapter.pagingloader

import android.content.res.ColorStateList
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.example.core.R
import com.example.core.extension.getThemeColor

abstract class BasePagingLoaderAdapter<VB : ViewBinding>(
    @ColorRes private val tint: Int = R.color.blue_light
) : BaseHeaderFooterAdapter<Any, VB>() {

    private var loadState: PagingLoaderState = PagingLoaderState.Init
        set(loadState) {
            if (field != loadState) {
                field = loadState
                notifyItemChanged(0)
            }
        }

    var isLoading = false
        set(value) {
            field = value
            loadState = if (value) {
                PagingLoaderState.Loading
            } else {
                PagingLoaderState.Done
            }
        }

    protected fun bindProgressBar(progressBar: ProgressBar) {
        progressBar.indeterminateTintList = ColorStateList.valueOf(
            ContextCompat.getColor(progressBar.context, tint)
        )
        progressBar.isVisible = loadState == PagingLoaderState.Loading
    }
}
