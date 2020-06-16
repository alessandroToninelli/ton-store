package com.toninelli.ton_store.data

import androidx.paging.PagingData
import com.toninelli.ton_store.model.Article
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import kotlinx.coroutines.flow.Flow

interface Repository {

     suspend fun getBeers(): Flow<Either<Failure,PagingData<Beer>>>

     suspend fun articleLastArrived(): Either<Failure, List<Article>>
}