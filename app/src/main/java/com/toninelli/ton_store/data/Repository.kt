package com.toninelli.ton_store.data

import androidx.paging.PagingData
import com.toninelli.ton_store.model.Beer
import kotlinx.coroutines.flow.Flow

interface Repository {

     suspend fun getBeers(): Flow<PagingData<Beer>>

}