package com.example.rickandmortyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.data.Character
import com.example.rickandmortyapp.network.RetrofitInstance
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)


        val characterId = intent.getIntExtra("character_id", -1)
        Log.d("CharacterDetailActivity", "Received Character ID: $characterId")

        val button : ImageView = findViewById(R.id.retour)

        button.setOnClickListener{
            val context = button.context
            val intent = Intent(context,MainActivity::class.java)
            context.startActivity(intent)


        }
        if (characterId != -1) {
            fetchCharacterDetails(characterId)
        } else {
            Log.e("CharacterDetailActivity", "Invalid Character ID")

        }

    }
    private fun fetchCharacterDetails(characterId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = RetrofitInstance.api.getCharacter(characterId)
                displayCharacterDetails(response)
            } catch (e: Exception) {
                // Handle exception
                Log.d("Error", "Erreur lors du chargement")
            }
        }
    }
    private fun displayCharacterDetails(character: Character) {
        val characterName : TextView = findViewById(R.id.nom)
        val characterOrigin : TextView = findViewById(R.id.origine)
        val characterLocation : TextView = findViewById(R.id.localisation)
        val characterGender : TextView = findViewById(R.id.genre)
        val characterStatus : TextView = findViewById(R.id.statut)
        val characterSpecies : TextView = findViewById(R.id.espece)
        val characterImage : ImageView = findViewById(R.id.picture)
        val toolbar : MaterialToolbar = findViewById(R.id.toolbardetail)

        toolbar.title = character.name
        characterName.text = character.name
        characterOrigin.text = character.origin.name
        characterLocation.text = character.location.name
        characterGender.text = character.gender
        characterStatus.text = character.status
        characterSpecies.text = character.species

        Glide.with(this)
            .load(character.image)
            .into(characterImage)
    }
}
