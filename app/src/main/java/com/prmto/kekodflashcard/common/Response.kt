package com.prmto.kekodflashcard.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class Response<out T> {
    data object Loading : Response<Nothing>()
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val message: String) : Response<T>()
}

inline fun <T, R> Response<T>.map(transform: (T) -> R): Response<R> {
    return when (this) {
        is Response.Success -> {
            Response.Success(transform(data))
        }

        is Response.Error -> {
            Response.Error(message)
        }

        is Response.Loading -> {
            Response.Loading
        }
    }
}

fun <T> Flow<Response<T>>.doOnSuccess(
    action: (T) -> Unit
): Flow<Response<T>> {
    return transform { value ->
        if (value is Response.Success) {
            action(value.data)
        }
        return@transform emit(value)
    }
}

fun <T> Flow<Response<T>>.doOnError(
    action: (String) -> Unit
): Flow<Response<T>> {
    return transform { value ->
        if (value is Response.Error) {
            action(value.message)
        }
        return@transform emit(value)
    }
}

fun <T> Flow<Response<T>>.doOnLoading(action: suspend () -> Unit): Flow<Response<T>> =
    transform { value ->
        if (value is Response.Loading) {
            action()
        }
        return@transform emit(value)
    }
