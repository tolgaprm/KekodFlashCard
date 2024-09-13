package com.prmto.kekodflashcard.data.remote.datasource

import android.util.Log
import com.prmto.kekodflashcard.common.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseDataSource {

    inline fun <T> apiCall(crossinline call: suspend () -> T): Flow<Response<T>> =
        flow {
            emit(Response.Loading)
            val result = call.invoke()
            emit(Response.Success(result))
        }.catch {
            Log.d("Helpers", "call: ${it.message}")
            emit(Response.Error("Bir hata meydana geldi. Tekrar Deneyiniz."))
        }
}