package com.toninelli.ton_store.binding



import com.toninelli.ton_store.vo.Resource
import kotlinx.coroutines.flow.Flow

interface BindableListAdapterData<T> {
    fun setData(data: Flow<Resource<T>>)

}

interface BindableListAdapterInformation<T>{
    fun setInformation(info: T)
}

