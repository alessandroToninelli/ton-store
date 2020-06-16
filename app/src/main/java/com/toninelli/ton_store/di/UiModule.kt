package com.toninelli.ton_store.di

import com.toninelli.ton_store.ui.main.MainActivity
import com.toninelli.ton_store.ui.main.catalog.CatalogFragment
import com.toninelli.ton_store.ui.main.catalog.CatalogViewModel
import com.toninelli.ton_store.ui.main.search.SearchFragment
import com.toninelli.ton_store.ui.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModule {
    val mainActivityModule = module {

        scope<MainActivity> {
            scope<CatalogFragment> {
                viewModel { CatalogViewModel(get()) }
            }

            scope<SearchFragment>{
                viewModel{ SearchViewModel()}
            }
        }

    }
}