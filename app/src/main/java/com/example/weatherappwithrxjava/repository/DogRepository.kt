package com.example.weatherappwithrxjava.repository

import com.example.weatherappwithrxjava.api.BaseApiResponse
import com.example.weatherappwithrxjava.api.DogResponse
import com.example.weatherappwithrxjava.model.RemoteDataSource
import com.example.weatherappwithrxjava.sealed.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse() {

    suspend fun getDog(): Flow<NetworkResult<DogResponse>> {
        return flow{
            emit(safeApiCall { remoteDataSource.getDog() })
        }.flowOn(Dispatchers.IO)
    }

}