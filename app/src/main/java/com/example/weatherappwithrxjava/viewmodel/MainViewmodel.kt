package com.example.weatherappwithrxjava.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherappwithrxjava.api.DogResponse
import com.example.weatherappwithrxjava.repository.DogRepository
import com.example.weatherappwithrxjava.sealed.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewmodel @Inject constructor(
    private val dogRepository: DogRepository,
    application: Application
) :AndroidViewModel(application){
    private val _response: MutableLiveData<NetworkResult<DogResponse>> = MutableLiveData()
    val response:LiveData<NetworkResult<DogResponse>> = _response

    fun fetchDogresponse() = viewModelScope.launch {
        dogRepository.getDog().collect { values ->
            _response.value = values

        }
    }
}