package com.example.lunchtray.repositories

sealed class ResultHistorico<T>(
    val Data: T? = null,
    val message: String? = null
){
    class Success<T>(data:T):ResultHistorico<T>(data)

    class Error<T>(message:String, data: T? = null):ResultHistorico<T>(data, message)

    class Loading<T>(data: T? = null):ResultHistorico<T>(data)
}
