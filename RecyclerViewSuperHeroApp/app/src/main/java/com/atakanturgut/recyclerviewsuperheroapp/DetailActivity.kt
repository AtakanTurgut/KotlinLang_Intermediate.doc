package com.atakanturgut.recyclerviewsuperheroapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atakanturgut.recyclerviewsuperheroapp.databinding.ActivityDetailBinding
import com.atakanturgut.recyclerviewsuperheroapp.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val adapterIntent = intent     // intent

        // val secilenKahraman = adapterIntent.getSerializableExtra("secilenKahraman", SuperHero::class.java)
        /* // intent
        val secilenKahraman = adapterIntent.getSerializableExtra("secilenKahraman") as SuperHero

        binding.imageView.setImageResource(secilenKahraman.gorsel)
        binding.textViewKarakter.text = secilenKahraman.karakter
        binding.textViewIsim.text = secilenKahraman.isim
        binding.textViewMeslek.text = secilenKahraman.meslek
        */

        val secilenKahraman = MySingleton.secilenSuperKahraman
        secilenKahraman?.let {      // singleton
            binding.imageView.setImageResource(secilenKahraman.gorsel)
            binding.textViewKarakter.text = secilenKahraman.karakter
            binding.textViewIsim.text = secilenKahraman.isim
            binding.textViewMeslek.text = secilenKahraman.meslek
        }

    }
}