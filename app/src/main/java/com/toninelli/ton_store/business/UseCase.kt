package com.toninelli.ton_store.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import com.toninelli.ton_store.vo.Resource
import com.toninelli.ton_store.vo.rightOrNull
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlin.coroutines.resume

interface Operation<T> {
    fun onNextValue(value: T?)
    fun onError(e: Failure)
    fun onCompletion()
}

abstract class UseCase<P, R> : BaseUseCase<P, Either<Failure, R>, R>() {


    override fun start(param: P?): Flow<Resource<R>> = callbackFlow {

        exec(param, object : Operation<Either<Failure, R>> {
            override fun onNextValue(value: Either<Failure, R>?) {
                value?.either(
                    {
                        val resource: Resource<R> = Resource.Error(it)
                        sendBlocking(resource)
                    },
                    {
                        val resource: Resource<R> = Resource.Success(it)
                        sendBlocking(resource)
                    })
            }

            override fun onError(e: Failure) {
                val resource: Resource<R> = Resource.Error(e)
                sendBlocking(resource)
                close()
            }

            override fun onCompletion() {
                close()
            }

        })

        awaitClose { println("callbackFlow completed") }
    }
}


abstract class BaseUseCase<P, O, R> {


    private val stateFlow = MutableStateFlow<Resource<R>>(Resource.Loading())

    val result: Flow<Resource<R>> get() = stateFlow

    operator fun invoke(param: P?, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            start(param).onCompletion { println("start completed $it") }.flowOn(Dispatchers.IO)
                .collect { stateFlow.value = it }
        }.invokeOnCompletion { println("use case terminated") }
    }

    protected abstract suspend fun exec(param: P?, operation: Operation<O>)

    abstract fun start(param: P?): Flow<Resource<R>>
}


fun <P, O, R> ViewModel.exec(
    useCase: BaseUseCase<P, O, R>,
    param: P? = null,
    scope: CoroutineScope = viewModelScope

) = useCase(param, scope)

