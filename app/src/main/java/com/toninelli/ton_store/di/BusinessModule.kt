package com.toninelli.ton_store.di

import com.toninelli.ton_store.business.BeerUseCase
import org.koin.dsl.module
import org.koin.experimental.builder.factory

object BusinessModule {

    val useCaseModule = module {
        factory<BeerUseCase>()
    }


}