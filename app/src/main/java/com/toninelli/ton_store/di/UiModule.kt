package com.toninelli.ton_store.di

import com.toninelli.ton_store.business.TestUseCase
import com.toninelli.ton_store.business.UseCase
import com.toninelli.ton_store.data.RepoImpl
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.ui.main.MainActivity
import com.toninelli.ton_store.ui.main.catalog.CatalogFragment
import com.toninelli.ton_store.ui.main.catalog.CatalogViewModel
import com.toninelli.ton_store.ui.main.search.SearchFragment
import com.toninelli.ton_store.ui.main.search.SearchViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.scoped
import org.koin.experimental.builder.scopedBy
import org.koin.experimental.builder.single
import org.koin.ext.getOrCreateScope

object UiModule {
    val mainActivityModule = module {

        scope<MainActivity> {
            scope<CatalogFragment> {
                viewModel { CatalogViewModel(get(), get()) }
            }

            scope<SearchFragment>{
                viewModel{ SearchViewModel(get())}
            }
        }

    }
}