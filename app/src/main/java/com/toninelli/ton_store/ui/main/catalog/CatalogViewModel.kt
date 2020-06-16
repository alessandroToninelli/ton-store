package com.toninelli.ton_store.ui.main.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.toninelli.ton_store.business.BeerUseCase
import com.toninelli.ton_store.business.exec


class CatalogViewModel(
    private val beerUseCase: BeerUseCase
) : ViewModel() {


    init {
        exec(beerUseCase)
    }

    val beer = beerUseCase.result.asLiveData()

}

