package com.example.rickandmortyapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.model.CharacterViewModel
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterAdapter = CharacterAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = characterAdapter
        val toolbar : MaterialToolbar = findViewById(R.id.toolbar)
        toolbar.title = "Rick & Morty"

        characterViewModel.characters.observe(this, Observer { characters ->
            characterAdapter.setCharacters(characters)
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition + 1 >= totalItemCount) {
                    characterViewModel.fetchCharacters()
                }
            }
        })
    }
}
