package com.toninelli.ton_store.data.datasource

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class PageDataSource<Type : Any> private constructor(): PagingSource<Int, Type>(){


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Type> {

        var result: LoadResult<Int, Type>? = null

        println(params is LoadParams.Refresh)

        getCall(params).either(
            {
                result =  LoadResult.Error(it)
            },
            {
                result = LoadResult.Page(
                    data = it,
                    nextKey = params.key?.inc() ?: 2,
                    prevKey = null
                )
            }
        )

        return result!!
    }

    override val jumpingSupported: Boolean
        get() = super.jumpingSupported

    abstract suspend fun getCall(params: LoadParams<Int>): Either<Failure, List<Type>>

    companion object PageBuilder {

        fun <Type : Any> build(pageSize: Int, call: suspend (params: LoadParams<Int>)-> Either<Failure, List<Type>>): Pager<Int, Type>{

            val source: PageDataSource<Type> = object : PageDataSource<Type>(){

                override suspend fun getCall(params: LoadParams<Int>): Either<Failure, List<Type>> {
                    return call(params)
                }
            }

            return Pager(PagingConfig(pageSize = pageSize, initialLoadSize = pageSize, enablePlaceholders = false)){source}

        }
    }
}