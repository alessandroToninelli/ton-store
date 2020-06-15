package com.toninelli.ton_store.ui.main.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import com.toninelli.ton_store.binding.BindableListAdapterData
import com.toninelli.ton_store.databinding.ItemBeerBinding
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.ui.common.BasePagedAdapter
import com.toninelli.ton_store.vo.Resource
import com.toninelli.ton_store.vo.case
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatalogItemAdapter(val owner: LifecycleOwner, val scope: CoroutineScope) :
    BasePagedAdapter<Beer, ItemBeerBinding>(diffCallback = object : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return false
        }

    }), BindableListAdapterData<PagingData<Beer>> {


    override fun onBindViewHolder(binding: ItemBeerBinding, item: Beer?, position: Int) {
        binding.beer = item
    }

    override fun inflateViewBind(parent: ViewGroup, viewType: Int): ItemBeerBinding {
        return ItemBeerBinding.inflate(LayoutInflater.from(parent.context))
    }

    override fun setData(data: Resource<PagingData<Beer>>) {
//        println("set data")
//        scope.launch {
//            data.collectLatest {
//                it.case(success = {
//                    it.data?.let { data ->
//                        submitData(data)
//                    }
//                })
//            }
//        }
        data.case(success = { list ->
            list.data?.let {
                submitData(owner.lifecycle, it)

            }
        })
    }
}