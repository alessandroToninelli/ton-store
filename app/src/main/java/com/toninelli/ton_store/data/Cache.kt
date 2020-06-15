package com.toninelli.ton_store.data

import com.dropbox.android.external.store4.*
import com.toninelli.ton_store.data.api.RemoteApi
import com.toninelli.ton_store.model.Article
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import kotlin.collections.get
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.hours
import kotlin.time.minutes

@ExperimentalTime
class Cache() {


    fun <R: Any> storeLastArrived(fetcher: (String)-> R): Store<String, R> {
        return StoreBuilder.from<String, R >(fetcher = nonFlowValueFetcher { fetcher(it) }).cachePolicy(
            MemoryPolicy.builder()
                .setExpireAfterAccess(1.minutes)
                .build()
        ).build()


    }


}


suspend inline fun <R : Any> Store<String, R>.getData(key: String, refresh: Boolean): R {
    return if (refresh)
        get(key)
    else
        fresh(key)
}
