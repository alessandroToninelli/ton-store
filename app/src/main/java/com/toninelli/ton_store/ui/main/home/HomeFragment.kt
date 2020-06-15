package com.toninelli.ton_store.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dropbox.android.external.store4.*
import com.toninelli.ton_store.R
import com.toninelli.ton_store.data.Cache
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.databinding.FragmentHomeBinding
import com.toninelli.ton_store.model.Article
import com.toninelli.ton_store.ui.main.MainActivity
import com.toninelli.ton_store.util.autoCleared
import com.toninelli.ton_store.vo.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeFragment(val onLoading: (Status) -> Unit) : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

    }
}



