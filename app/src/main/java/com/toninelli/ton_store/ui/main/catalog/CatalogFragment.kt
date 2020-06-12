package com.toninelli.ton_store.ui.main.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.toninelli.ton_store.R
import com.toninelli.ton_store.databinding.FragmentCatalogBinding
import com.toninelli.ton_store.util.autoCleared
import com.toninelli.ton_store.util.toStatus
import com.toninelli.ton_store.vo.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.lifecycleScope as koinScope
import org.koin.androidx.viewmodel.scope.viewModel

class CatalogFragment(private val onLoading: (Status) -> Unit) : Fragment(R.layout.fragment_catalog) {

    val model: CatalogViewModel by koinScope.viewModel(this)

    private var binding by autoCleared<FragmentCatalogBinding>()
    private var catalogItemAdapter by autoCleared<CatalogItemAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.model = model

        model.getPosts()

        catalogItemAdapter = CatalogItemAdapter(lifecycleScope)
        binding.list.adapter = catalogItemAdapter

        binding.swipeRefresh.setOnRefreshListener { catalogItemAdapter.refresh() }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            catalogItemAdapter.loadStateFlow.collect {
                onLoading(it.refresh.toStatus())
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
    }


}