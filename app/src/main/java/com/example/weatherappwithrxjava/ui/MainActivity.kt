package com.example.weatherappwithrxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Transformation
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.weatherappwithrxjava.R
import com.example.weatherappwithrxjava.databinding.ActivityMainBinding
import com.example.weatherappwithrxjava.sealed.NetworkResult
import com.example.weatherappwithrxjava.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewmodel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()

        binding.imgRefresh.setOnClickListener {
            fetchResponse()
        }
    }

    private fun fetchData() {
        fetchResponse()
        mainViewModel.fetchDogresponse()
        mainViewModel.response.observe(this){ response ->
            when(response){
                is NetworkResult.Success ->{
                    response.data?.let {
                        binding.imgDog.load(
                            response.data.message
                        ){
                            transformations(RoundedCornersTransformation(16f))
                        }
                    }
                      binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error ->{
                    //Show the error message
                    binding.pbDog.visibility = View.GONE
                    Toast.makeText(this,"Can't fetch, check your connection",Toast.LENGTH_LONG).show()
                }

                is NetworkResult.Loading ->{
                    //Loading progress bar
                    binding.pbDog.visibility = View.VISIBLE


                }
            }
        }
    }

    private fun fetchResponse() {
        mainViewModel.fetchDogresponse()
        binding.pbDog.visibility = View.VISIBLE
    }
}