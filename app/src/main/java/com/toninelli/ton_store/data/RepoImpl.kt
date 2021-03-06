package com.toninelli.ton_store.data

import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.networkcalladapterlib.*
import com.toninelli.ton_store.data.api.ApiMock
import com.toninelli.ton_store.data.api.RemoteApi
import com.toninelli.ton_store.data.datasource.PageDataSource
import com.toninelli.ton_store.model.Article
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.util.either
import com.toninelli.ton_store.vo.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import okhttp3.ResponseBody
import kotlin.coroutines.coroutineContext

class RepoImpl(val api: RemoteApi): Repository {


    override  suspend fun getBeers(): Flow<Either<Failure, PagingData<Beer>>> {
        val pager = PageDataSource.build(40, 1) {
            with(api.getBeers(it.key ?: 1, it.loadSize)) {
                when (this) {
                    is ResponseNetworkSuccess -> right(this.body)
                    is ResponseNetworkSuccessEmpty -> left(Failure.EmptyData())
                    is ResponseNetworkError -> left(Failure.NetworkFailure(this.code))
                    is ResponseNetworkIOFailure -> left(Failure.NoConnection)
                    is ResponseNetworkUnknownError -> left(Failure.NoConnection)
                }
            }
        }.flow

        return pager.either { Failure.Error(msg = it.localizedMessage ?: "unknown error") }
    }


    override suspend fun articleLastArrived(): Either<Failure, List<Article>> {
        return ApiMock.lastArrived(3).either(
            {
                right(it.body)
            },
            {
                left(Failure.NetworkFailure(it.code))
            })
    }

}


