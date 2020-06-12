package com.toninelli.ton_store.util

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.toninelli.ton_store.vo.Status

fun LoadState.toStatus(): Status{
    val isError = this is LoadState.Error
    val isLoading = this is LoadState.Loading
    return when {
        isError -> Status.ERROR
        isLoading -> Status.LOADING
        else -> Status.SUCCESS
    }
}