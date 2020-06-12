package com.toninelli.ton_store.di

import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules

class KoinManager: KoinComponent {

    companion object {

        fun loadModules() {
            loadKoinModules(
                listOf(
                    DataModule.netModule,
                    DataModule.repoModule,
                    UiModule.mainActivityModule
                )
            )
        }
    }


}