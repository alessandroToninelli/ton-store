package com.toninelli.ton_store

import android.app.Application
import com.toninelli.ton_store.di.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class StoreApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@StoreApplication)
            fragmentFactory()
            KoinManager.loadModules()
        }
    }
}