package com.example.rickandmortyapp
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.model.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterAdapter = CharacterAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = characterAdapter

        characterViewModel.characters.observe(this, Observer { characters ->
            characterAdapter.setCharacters(characters)
        })
    }
}
