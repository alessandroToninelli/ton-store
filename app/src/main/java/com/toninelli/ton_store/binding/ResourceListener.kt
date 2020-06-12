package com.toninelli.ton_store.binding

import com.toninelli.ton_store.vo.Resource

interface ResourceListener {
    fun <T> onResourceSuccess(resource: Resource<T>) {}
    fun onResourceError(exception: Exception) {}
    fun onResourceLoading() {}
}