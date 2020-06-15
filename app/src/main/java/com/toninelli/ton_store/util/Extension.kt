package com.toninelli.ton_store.util

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.networkcalladapterlib.*
import com.toninelli.ton_store.vo.*

fun LoadState.toStatus(): Status {
    val isError = this is LoadState.Error
    val isLoading = this is LoadState.Loading
    return when {
        isError -> Status.ERROR
        isLoading -> Status.LOADING
        else -> Status.SUCCESS
    }
}

inline fun <S : Any, E : Any> ResponseNetwork<S, E>.either(
    onSuccess: (ResponseNetworkSuccess<S>) -> Either<Failure, S>,
    onError: (ResponseNetworkError<E>) -> Either<Failure, S>,
    onSuccessEmpty: (ResponseNetworkSuccessEmpty) -> Either.Left<Failure> = { left(Failure.EmptyData()) },
    onConnectionError: (ResponseNetworkIOFailure) -> Either.Left<Failure> = { left(Failure.NoConnection) },
    onUnknownError: (ResponseNetworkUnknownError) -> Either.Left<Failure> = { left(Failure.Error(it.error)) }
): Either<Failure, S> {
    return when (this) {
        is ResponseNetworkSuccess -> onSuccess(this)
        is ResponseNetworkSuccessEmpty -> onSuccessEmpty(this)
        is ResponseNetworkError -> onError(this)
        is ResponseNetworkIOFailure -> onConnectionError(this)
        is ResponseNetworkUnknownError -> onUnknownError(this)
    }
}