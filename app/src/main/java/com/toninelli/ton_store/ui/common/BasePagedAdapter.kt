package com.toninelli.ton_store.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toninelli.ton_store.databinding.ItemBeerBinding

abstract class BasePagedAdapter<Item, Binding>(
    diffCallback: DiffUtil.ItemCallback<Item>
) : PagingDataAdapter<Item, BasePagedAdapter.PagedBaseViewHolder<Binding>>(diffCallback) where Binding : ViewDataBinding, Item : Any {

    protected lateinit var binding: Binding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagedBaseViewHolder<Binding> {

        binding = inflateViewBind(parent, viewType)

        onCreateViewHolder(binding, viewType)

        return PagedBaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagedBaseViewHolder<Binding>, position: Int) {
        onBindViewHolder(holder.binding, getItem(position), position)
    }


    open fun onCreateViewHolder(binding: Binding, viewType: Int) {}

    abstract fun onBindViewHolder(binding: Binding, item: Item?, position: Int)

    abstract fun inflateViewBind(parent: ViewGroup, viewType: Int): Binding

    class PagedBaseViewHolder<out T : ViewDataBinding> constructor(val binding: T) :
        RecyclerView.ViewHolder(binding.root)

}