package com.toninelli.ton_store.business

import androidx.paging.PagingData
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.model.Beer
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class BeerUseCase (private val repo: Repository): UseCase<Nothing, PagingData<Beer>>(){
    override suspend fun exec(
        param: Nothing?,
        onResult: (Either<Failure, PagingData<Beer>>) -> Unit
    ) {
        repo.getBeers().collect { onResult(it) }
    }

}