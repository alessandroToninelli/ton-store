package com.toninelli.ton_store.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.toninelli.ton_store.R
import com.toninelli.ton_store.databinding.FragmentMainBinding
import com.toninelli.ton_store.ui.main.catalog.CatalogFragment
import com.toninelli.ton_store.ui.main.home.HomeFragment
import com.toninelli.ton_store.ui.main.lastarrived.LastArrivedFragment
import com.toninelli.ton_store.ui.main.offer.OfferFragment
import com.toninelli.ton_store.util.autoCleared
import com.toninelli.ton_store.vo.Status


class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding by autoCleared<FragmentMainBinding>()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)



        val pagerAdapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4

            override fun createFragment(position: Int): Fragment {

                return when (position) {
                    0 -> HomeFragment(onLoading)
                    1 -> CatalogFragment(onLoading)
                    2 -> LastArrivedFragment(onLoading)
                    3 -> OfferFragment(onLoading)
                    else -> HomeFragment(onLoading)
                }
            }

        }
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val titleId = when (position) {
                0 -> R.string.label_home
                1 -> R.string.label_catalog
                2 -> R.string.label_last_arrived
                3 -> R.string.label_offer
                else -> R.string.label_home
            }

            tab.text = getString(titleId)
        }.attach()
    }

    val onLoading : (Status) -> Unit  = {
        binding.status = it
    }

}