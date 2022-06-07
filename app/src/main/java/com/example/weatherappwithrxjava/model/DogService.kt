package com.example.weatherappwithrxjava.model

import com.example.weatherappwithrxjava.api.DogResponse
import com.example.weatherappwithrxjava.constants.Constants
import retrofit2.Response
import retrofit2.http.GET

interface DogService {

       @GET(Constants.RANDOM_URL)
       suspend fun  getDog():Response<DogResponse>


}