package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters: List<Character> = listOf()

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val statusTextView: TextView = view.findViewById(R.id.statusTextView)
        private val speciesTextView: TextView = view.findViewById(R.id.speciesTextView)
        private val imageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(character: Character) {
            nameTextView.text = character.name
            statusTextView.text = character.status
            speciesTextView.text = character.species
            Glide.with(itemView.context).load(character.image).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}
