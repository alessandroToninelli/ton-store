package com.toninelli.ton_store.data.datasource

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class PageDataSource<Type : Any> private constructor(private val initialPage: Int) :
    PagingSource<Int, Type>() {

    private var startPage = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Type> {

        var result: LoadResult<Int, Type>? = null

        when(params){
            is LoadParams.Refresh -> {
                startPage = initialPage
            }
        }
        getCall(params).either(
            {
                result = LoadResult.Error(it)
            },
            {
                startPage = startPage.inc()
                result = if (it.isEmpty())
                    LoadResult.Error(Failure.EmptyData())
                else {
                    LoadResult.Page(
                        data = it,
                        nextKey = startPage,
                        prevKey = null
                    )
                }
            }
        )

        return result!!
    }

    abstract suspend fun getCall(params: LoadParams<Int>): Either<Failure, List<Type>>

    companion object PageBuilder {

        fun <Type : Any> build(
            pageSize: Int,
            initialPage: Int,
            call: suspend (params: LoadParams<Int>) -> Either<Failure, List<Type>>
        ): Pager<Int, Type> {

            val source: PageDataSource<Type> = object : PageDataSource<Type>(initialPage) {

                override suspend fun getCall(params: LoadParams<Int>): Either<Failure, List<Type>> {
                    return call(params)
                }
            }

            return Pager(
                PagingConfig(
                    pageSize = pageSize,
                    initialLoadSize = pageSize,
                    enablePlaceholders = false
                )
            ) { source }

        }
    }
}