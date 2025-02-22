package com.atakanturgut.androidalertdialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.atakanturgut.androidalertdialog.databinding.ActivityMainBinding

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

        // Context
        // Aktivite Context - App Context
                       // this - this@MainActivity
        Toast.makeText(applicationContext, "Hoşgeldiniz", Toast.LENGTH_LONG).show()

        /*
        binding.button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                println("butona tıklanıldı")
            }
        })

        binding.button.setOnClickListener {  }
        */

    }

    fun kaydet(view : View) {
        val alert = AlertDialog.Builder(this@MainActivity)

        alert.setTitle("Kayıt Et")
        alert.setMessage("Kayıt etmek istediğinize emin misiniz?")

        alert.setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(this@MainActivity,"Kayıt başarılı!", Toast.LENGTH_LONG).show()
        })

        alert.setNegativeButton("Hayır", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(this@MainActivity,"Kayıt iptal edildi!", Toast.LENGTH_LONG).show()
            }
        })

        alert.show()
    }
}