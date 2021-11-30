package com.example.app_sudamericana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonlogin = findViewById<Button>(R.id.Btnlogin)
        botonlogin.setOnClickListener {
            val lanzar = Intent( this, HomeActivity::class.java)
            startActivity(lanzar)}


        val Textregistrar = findViewById<TextView>(R.id.TxtRegistrate)
        Textregistrar.setOnClickListener {
            val lanzar = Intent ( this, RegistroActivity::class.java)
            startActivity(lanzar)}
    }
}