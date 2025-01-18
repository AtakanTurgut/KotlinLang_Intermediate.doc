package com.atakanturgut.simplecalculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atakanturgut.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var birinciSayi : Double? = null
    var ikinciSayi : Double? = null
    var sonuc : Double? = null

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
    }

    fun topla(view : View) {
        birinciSayi = binding.editTextFirstNum.text.toString().toDoubleOrNull()
        ikinciSayi = binding.editTextSecondNum.text.toString().toDoubleOrNull()

        if (birinciSayi == null || ikinciSayi == null) {
            binding.textViewConclusion.text = "Numaraları giriniz!"
        } else {
            sonuc = birinciSayi!! + ikinciSayi!!

            binding.textViewConclusion.text = "Sonuç: ${sonuc}"
        }
    }

    fun cikar(view : View) {
        birinciSayi = binding.editTextFirstNum.text.toString().toDoubleOrNull()
        ikinciSayi = binding.editTextSecondNum.text.toString().toDoubleOrNull()

        if (birinciSayi == null || ikinciSayi == null) {
            binding.textViewConclusion.text = "Numaraları giriniz!"
        } else {
            sonuc = birinciSayi!! - ikinciSayi!!

            binding.textViewConclusion.text = "Sonuç: ${sonuc}"
        }
    }

    fun carp(view : View) {
        birinciSayi = binding.editTextFirstNum.text.toString().toDoubleOrNull()
        ikinciSayi = binding.editTextSecondNum.text.toString().toDoubleOrNull()

        if (birinciSayi == null || ikinciSayi == null) {
            binding.textViewConclusion.text = "Numaraları giriniz!"
        } else {
            sonuc = birinciSayi!! * ikinciSayi!!

            binding.textViewConclusion.text = "Sonuç: ${sonuc}"
        }
    }

    fun bol(view : View) {
        birinciSayi = binding.editTextFirstNum.text.toString().toDoubleOrNull()
        ikinciSayi = binding.editTextSecondNum.text.toString().toDoubleOrNull()

        if (birinciSayi == null || ikinciSayi == null) {
            binding.textViewConclusion.text = "Numaraları giriniz!"
        } else {
            sonuc = birinciSayi!! / ikinciSayi!!

            binding.textViewConclusion.text = "Sonuç: ${sonuc}"
        }
    }
}