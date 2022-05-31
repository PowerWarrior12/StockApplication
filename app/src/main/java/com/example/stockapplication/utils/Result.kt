package com.example.stockapplication.utils

import java.lang.Exception

sealed class Result<out R> {

    abstract fun <A> map(f: (R) -> A): Result<A>

    data class Success<out T>(val data: T) : Result<T>() {
        override fun <A> map(f: (T) -> A): Result<A> {
            return Success(f(data))
        }
    }

    data class Error(val exception: Exception) : Result<Nothing>() {
        override fun <A> map(f: (Nothing) -> A): Result<A> {
            return Error(exception)
        }
    }

    companion object {
        fun <A> sequence(list: List<Result<A>>): Result<List<A>> {
            val listResult = mutableListOf<A>()
            for (result in list) {
                if (result is Success) listResult.add(result.data)
                else return Error((result as Error).exception)
            }
            return Success<List<A>>(listResult)
        }
    }
}
