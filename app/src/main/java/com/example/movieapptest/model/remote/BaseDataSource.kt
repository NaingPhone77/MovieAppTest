package com.example.movieapptest.model.remote

import com.example.movieapptest.helper.Resource
import retrofit2.HttpException
import retrofit2.Response

open class BaseDataSource {


    protected suspend fun <T> getResultOrError(request: suspend () -> Response<T>) : Resource<T> {


        val response = request()
        try {
            if(response.isSuccessful) {
                val body = response.body()
                if(body != null) return Resource.Success(body)
            }
            return Resource.Error(
                "${response.body()} ${response.code()}",
                Throwable(response.code().toString())
            )
        }catch (error: HttpException) {
            return error(error.message ?: error.toString())
        }
    }

    private fun <T> error(message: String) : Resource<T> {
        return Resource.Error(message,Throwable(message))
    }

}