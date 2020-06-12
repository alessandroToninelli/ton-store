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


abstract class UseCase<P, R> : BaseUseCase<P,R>() {


    override suspend fun start(param: P?): Flow<Resource<R>> = callbackFlow {

        exec(param) { result ->
            result.either(
                {
                    val resource: Resource<R> = Resource.Error(it)
                    sendBlocking(resource)
                    close()
                },
                {
                    val resource: Resource<R> = Resource.Success(it)
                    sendBlocking(resource)
                    close()
                })
        }

        awaitClose { println("stream chiuso") }
    }
}


abstract class BaseUseCase<P, R> {


    private val stateFlow = MutableStateFlow<Resource<R>>(Resource.Loading())

    val result: Flow<Resource<R>> get() = stateFlow

    operator fun invoke(param: P?, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            start(param).flowOn(Dispatchers.IO).collect { stateFlow.value = it }
        }
    }

    protected abstract suspend fun exec(param: P?, onResult: (Either<Failure,R>) -> Unit)

    abstract suspend fun start(param: P?): Flow<Resource<R>>
}


fun <P, R> ViewModel.exec(
    useCase: BaseUseCase<P, R>,
    param: P? = null,
    scope: CoroutineScope = viewModelScope

) = useCase(param, scope)

