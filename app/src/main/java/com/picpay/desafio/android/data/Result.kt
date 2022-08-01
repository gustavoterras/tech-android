package com.picpay.desafio.android.data

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val e: Throwable) : Result<T>()
}