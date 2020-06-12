package com.toninelli.ton_store.ui.main.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.toninelli.ton_store.R
import com.toninelli.ton_store.databinding.FragmentCatalogBinding
import com.toninelli.ton_store.util.autoCleared
import org.koin.androidx.scope.lifecycleScope as koinScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.scope.viewModel

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    val model: CatalogViewModel by koinScope.viewModel(this)

    private var binding by autoCleared<FragmentCatalogBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        binding.model = model

        model.getPosts()

        val adapter = CatalogItemAdapter(lifecycleScope)
        binding.list.adapter = adapter
    }


}