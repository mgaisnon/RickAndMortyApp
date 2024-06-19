package com.example.rickandmortyapp.model

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.rickandmortyapp.data.Character
import com.example.rickandmortyapp.data.CharacterResponse
import com.example.rickandmortyapp.network.RetrofitInstance

class CharacterViewModel : ViewModel() {
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    private var currentPage = 1
    private var isLoading = false
    private val allCharacters = mutableListOf<Character>()

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val response: CharacterResponse = RetrofitInstance.api.getCharacters(currentPage)
                allCharacters.addAll(response.results)
                _characters.value = allCharacters
                currentPage++
            } catch (e: Exception) {
                // Gestion des exceptions
            } finally {
                isLoading = false
            }
        }
    }
}
