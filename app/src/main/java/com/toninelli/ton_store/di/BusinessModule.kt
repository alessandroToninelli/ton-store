package com.toninelli.ton_store.di

import com.toninelli.ton_store.business.BeerUseCase
import com.toninelli.ton_store.business.TestUseCase
import org.koin.dsl.module
import org.koin.experimental.builder.single

object BusinessModule {

    val useCaseModule = module {
        single<TestUseCase>()
        single<BeerUseCase>()
    }


}