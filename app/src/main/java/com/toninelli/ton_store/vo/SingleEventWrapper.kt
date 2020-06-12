package com.toninelli.ton_store.vo

class SingleEventWrapper<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContent(): T?{
        return if (hasBeenHandled)
            null
        else{
            hasBeenHandled = true
            content
        }
    }


}