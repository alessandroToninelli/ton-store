package com.toninelli.ton_store.ui.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.toninelli.ton_store.R
import com.toninelli.ton_store.databinding.FragmentSearchBinding
import com.toninelli.ton_store.util.autoCleared
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.androidx.scope.lifecycleScope as koinScope

class SearchFragment : Fragment(R.layout.fragment_search) {


    var binding by autoCleared<FragmentSearchBinding>()

    val model: SearchViewModel by koinScope.viewModel(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}