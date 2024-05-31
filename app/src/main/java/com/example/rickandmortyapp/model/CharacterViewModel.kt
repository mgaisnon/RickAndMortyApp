package com.example.rickandmortyapp.model

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.rickandmortyapp.data.Character
import com.example.rickandmortyapp.data.CharacterResponse
import com.example.rickandmortyapp.network.RetrofitInstance

class CharacterViewModel : ViewModel() {
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response: CharacterResponse = RetrofitInstance.api.getCharacters(1)
                _characters.value = response.results
            } catch (e: Exception) {
                // Gestion des exceptions
            }
        }
    }
}
