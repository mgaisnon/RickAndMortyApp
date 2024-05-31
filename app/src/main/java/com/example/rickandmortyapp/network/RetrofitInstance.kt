package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.data.RickAndMortyApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RickAndMortyApiService by lazy {
        retrofit.create(RickAndMortyApiService::class.java)
    }
}
