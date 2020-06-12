package com.toninelli.ton_store.ui.main.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CatalogViewModel (val repo: Repository): ViewModel(){


    fun getPosts(){
        exec()
    }


    private val flow = MutableStateFlow<Resource<PagingData<Beer>>>(Resource.Loading())

    val beer: Flow<Resource<PagingData<Beer>>> get() = flow

    fun exec(){

        viewModelScope.launch {

            val result = withContext(Dispatchers.IO){repo.getBeers()}
            result.flowOn(Dispatchers.Default).collect { flow.value = Resource.Success(it) }
        }
    }

}

