package com.atakanturgut.lifecycle

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atakanturgut.lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        println("onCreate worked")  // 0 - kullanıcı görmüyor
                                    // onDestroy yapıldığı zaman tekrar çalışır
    }

    override fun onStart() {
        super.onStart()
        println("onStart worked")   // 1 - arayüz gözükür fakat etkileşime geçiş yapılır
                                    // onDestroy yapılmaz ise uygulama tekrar açıldığında buradan çalışır
    }

    override fun onResume() {
        super.onResume()
        println("onResume worked")  // 2 - Kullanıcı uygulamayı görmeye başlar
    }

    override fun onPause() {        // 3 - uygulama durduruldu
        super.onPause()
        println("onPause worked")
    }

    override fun onStop() {         // 4 - uygulama kapatıldı
        super.onStop()
        println("onStop worked")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy worked") // 5 - uygulama tamamen kapatıldığı zaman çalışır
                                    // arkada açık olan uygulamalardan kapatılması durumu
    }

    fun sonrakiSayfa(view : View) {
        // intent farklı activity'ler ile ilgili işlemler için kullanılır

        // context
        val intent = Intent(this, SecondActivity :: class.java) // :: referans verir

        val kullaniciGirdisi = binding.editText.text.toString()
        intent.putExtra("isim", kullaniciGirdisi)

        startActivity(intent)
        //finish()    // Destroy işlemi yapar - girilenler hafızadan silinir

        binding.textView.text = "İsim: ${kullaniciGirdisi}"
    }
}