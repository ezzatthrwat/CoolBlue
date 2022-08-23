package com.example.core.adapter.pagingloader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.core.adapter.BindingViewHolder

abstract class BaseHeaderFooterAdapter<T, VB : ViewBinding> :
    RecyclerView.Adapter<BindingViewHolder<VB>>() {

    private val items = mutableListOf<T>()

    abstract fun onCreateBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    abstract fun onBindItem(binding: VB, item: T?)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<VB> {
        val binding = onCreateBinding(LayoutInflater.from(parent.context), parent)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<VB>, position: Int) =
        onBindItem(holder.binding, items.getOrNull(position))

    override fun getItemCount(): Int = 1

    fun submitItem(item: T) {
        items.clear()
        items.add(item)
        notifyItemChanged(0)
    }
}
