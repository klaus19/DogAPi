package com.example.weatherappwithrxjava.api

import com.example.weatherappwithrxjava.sealed.NetworkResult
import retrofit2.Response
import java.lang.Exception


abstract class BaseApiResponse {

    suspend fun <T>safeApiCall(apiCall:suspend()->Response<T>):NetworkResult<T>{
        try {
            val response = apiCall()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.body()}")
        }catch (e:Exception){
            return  error(e.message?:e.toString())
        }
    }

    private fun<T> error(errormessage:String):NetworkResult<T> =
        NetworkResult.Error("Api call failed $errormessage")
}