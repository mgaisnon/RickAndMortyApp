package com.example.rickandmortyapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character
}