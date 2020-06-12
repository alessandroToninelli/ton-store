package com.toninelli.ton_store.vo

sealed class PagedStatus(error: Failure? = null) {

    object Success: PagedStatus()

    data class Error(val error: Failure): PagedStatus(error)


}