package com.atakanturgut.layoutandviews

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atakanturgut.layoutandviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        (1)
        val image = findViewById<ImageView>(R.id.imageView)
        image.setImageResource(R.drawable.istanbul)

        val benimTextView = findViewById<TextView>(R.id.textView)
        benimTextView.text = "Merhaba Kotlin"
        */

        binding.textView.text = "Merhaba Atakan"
        /*
        (2)
        binding.button1.setOnClickListener{
            binding.textView.text = "Butona Tıklandı"
        }
        */
    }

    // (3) - onClick()
    /*
    metot ismi onClick - atrribute'ye atanan isim ile aynı olacak
    metotlar private olmayacak
    View - sınıf olarak parametre alacak -- alt enter
    */
    fun kaydet(view: View) {
        binding.textView.text = "Giriş Yapıldı"
    }

    fun iptal(view: View) {
        binding.textView.text = "İşlem İptal Edildi"
    }
}