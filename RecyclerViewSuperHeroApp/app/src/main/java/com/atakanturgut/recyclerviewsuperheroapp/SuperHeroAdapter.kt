package com.atakanturgut.recyclerviewsuperheroapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atakanturgut.recyclerviewsuperheroapp.databinding.RecyclerRowBinding

class SuperHeroAdapter(val superKahramanListesi : ArrayList<SuperHero>) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {
    class SuperHeroViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false) // layout ve xml birleştirme işlemi

        return  SuperHeroViewHolder(binding)
    }

    override fun getItemCount(): Int {  // Kaç satır - row ekleneceği
        return superKahramanListesi.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.binding.textViewRecyclerView.text = superKahramanListesi[position].karakter

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            MySingleton.secilenSuperKahraman = superKahramanListesi[position]       // singleton
            // intent.putExtra("secilenKahraman", superKahramanListesi[position])   // intent
            holder.itemView.context.startActivity(intent)
        }
    }
}