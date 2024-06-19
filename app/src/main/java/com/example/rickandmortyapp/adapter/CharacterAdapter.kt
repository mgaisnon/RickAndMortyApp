package com.example.rickandmortyapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.CharacterActivity
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.Character
import kotlin.math.log

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters: List<Character> = listOf()

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemViewclick: LinearLayout = view.findViewById(R.id.ItemView)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val statusTextView: TextView = view.findViewById(R.id.statusTextView)
        private val speciesTextView: TextView = view.findViewById(R.id.speciesTextView)
        private val imageView: ImageView = view.findViewById(R.id.picture)

        fun bind(character: Character) {
            nameTextView.text = character.name
            statusTextView.text = character.status
            speciesTextView.text = character.species
            Glide.with(itemView.context).load(character.image).into(imageView)

            itemViewclick.setOnClickListener{
                Log.d("CharacterAdapter", "Clicked on: ${character.name}, ID: ${character.id}")

                val context = itemView.context
                val intent = Intent(context,CharacterActivity::class.java)
                intent.putExtra("character_id", character.id)

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

    }

    override fun getItemCount(): Int {
        return characters.size
    }
}
