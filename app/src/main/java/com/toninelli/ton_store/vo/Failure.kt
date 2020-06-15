package com.toninelli.ton_store.vo


sealed class Failure(msg: String) : Exception(msg) {

    class UnknownError(msg: String?  = null) : Failure(msg ?: "Unknown error")
    class Error(val e: Throwable? = null, msg: String  = e?.localizedMessage ?: "Error") : Failure(msg)
    object NoConnection : Failure("No connection")
    class LoginError(msg: String) : Failure(msg)
    class EmptyData(msg: String = "Empty Data") : Failure(msg)
    class NetworkFailure(cod: Int, msg: String = "Network Error") : Failure(msg)
}



