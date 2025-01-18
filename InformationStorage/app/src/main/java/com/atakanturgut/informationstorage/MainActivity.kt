package com.atakanturgut.informationstorage

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atakanturgut.informationstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Shared Preferences
    lateinit var sharedPreferences: SharedPreferences
    var saklananKullaniciIsmi : String? = null

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

        sharedPreferences =
            this.getSharedPreferences("com.atakanturgut.informationstorage", MODE_PRIVATE)
            // Oluştrulan dosyaya başka uygulamalarla erişemez

        saklananKullaniciIsmi = sharedPreferences.getString("isim","")

        if (saklananKullaniciIsmi == "") {
            binding.textView.text = "Kaydedilen İsim:"
        } else {
            binding.textView.text = "Kaydedilen İsim: ${saklananKullaniciIsmi}"
        }
    }

    fun kaydet(view : View) {
        val kullaniciIsmi = binding.editText.text.toString()

        if (kullaniciIsmi == "") {
            Toast.makeText(this@MainActivity, "İsim alanı boş bırakılamaz!", Toast.LENGTH_LONG).show()
        } else {
            sharedPreferences.edit().putString("isim", kullaniciIsmi).apply()
            binding.textView.text = "Kaydedilen İsim: ${kullaniciIsmi}"
        }
    }

    fun sil(view : View) {
        saklananKullaniciIsmi = sharedPreferences.getString("isim","")

        if (saklananKullaniciIsmi != "") {
            sharedPreferences.edit().remove("isim").apply()
        }

        binding.textView.text = "Kaydedilen İsim:"
    }
}