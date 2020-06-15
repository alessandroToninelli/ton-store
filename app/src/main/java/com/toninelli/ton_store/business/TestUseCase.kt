package com.toninelli.ton_store.business

import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.vo.Either
import com.toninelli.ton_store.vo.Failure
import com.toninelli.ton_store.vo.right
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

class TestUseCase(private val repo: Repository) : UseCase<Nothing, Int>() {
    override suspend fun exec(param: Nothing?, operation: Operation<Either<Failure, Int>>) {

        println(Thread.currentThread())
        val a = flow {
            repeat(10) {
                delay(1000)
                emit(it)

            }
        }

        a.catch { println("onCatch"); operation.onError(Failure.Error(it)) }.onCompletion { operation.onCompletion()}
            .collect {
                operation.onNextValue(
                    right(it)
                )
            }

    }

}