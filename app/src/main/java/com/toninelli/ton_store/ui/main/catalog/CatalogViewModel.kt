package com.toninelli.ton_store.ui.main.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.toninelli.ton_store.business.BeerUseCase
import com.toninelli.ton_store.business.TestUseCase
import com.toninelli.ton_store.business.exec
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CatalogViewModel(
    private val testUseCase: TestUseCase,
    private val beerUseCase: BeerUseCase
) : ViewModel() {


    init {
        exec(beerUseCase)
//        exec(testUseCase)

    }



    val test = testUseCase.result
    val beer = beerUseCase.result.asLiveData()



//    private val flow = MutableStateFlow<Resource<PagingData<Beer>>>(Resource.Loading())
//
//    val beer: Flow<Resource<PagingData<Beer>>> = flow
//
//    fun exec() {
//
//        bee
//        viewModelScope.launch {
//
//            val result = withContext(Dispatchers.IO) { repo.getBeers() }
//            result.flowOn(Dispatchers.Default).collect { flow.value = Resource.Success(it) }
//        }
//    }

}

