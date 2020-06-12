package com.toninelli.ton_store.business

import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure

class TestUseCase(private val repo: Repository) : UseCase<Nothing, Int>() {
    override suspend fun exec(param: Nothing?, onResult: (Either<Failure, Int>) -> Unit) {
        onResult(repo.test())
    }

}