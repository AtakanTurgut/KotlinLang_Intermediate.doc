package com.atakanturgut.recyclerviewsuperheroapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.atakanturgut.recyclerviewsuperheroapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var superKahramanListesi : ArrayList<SuperHero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val superman = SuperHero("Superman", "Clark Kent", "Gazeteci", R.drawable.superman)
        val batman = SuperHero("Batman", "Bruce Wayne", "İş İnsanı", R.drawable.batman)
        val ironman = SuperHero("Iron Man", "Tony Stark", "İş İnsanı", R.drawable.ironman)
        val spiderman = SuperHero("Spider-Man", "Peter Parker", "Fotoğrafçı", R.drawable.spiderman)

        superKahramanListesi = arrayListOf(superman, batman, ironman, spiderman)

        val adapter = SuperHeroAdapter(superKahramanListesi)

        binding.superHeroRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.superHeroRecyclerView.adapter = adapter
    }
}