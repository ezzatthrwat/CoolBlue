package com.example.shopping.presentation.search.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.presentation.search.model.ProductUiModel

object ProductsDiffUtil : DiffUtil.ItemCallback<ProductUiModel>() {

    override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
        return oldItem == newItem
    }
}
