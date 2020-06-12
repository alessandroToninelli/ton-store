package com.toninelli.ton_store.di

import com.toninelli.ton_store.data.RepoImpl
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.ui.main.MainActivity
import com.toninelli.ton_store.ui.main.catalog.CatalogFragment
import com.toninelli.ton_store.ui.main.catalog.CatalogViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.scoped
import org.koin.experimental.builder.scopedBy

object UiModule {
    val mainActivityModule = module {

        scope<MainActivity> {
            fragment { CatalogFragment() }
            scope<CatalogFragment> {
                scopedBy<Repository, RepoImpl>()
                viewModel { CatalogViewModel(get()) }
            }
        }

    }
}