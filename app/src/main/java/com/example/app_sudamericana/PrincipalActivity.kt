package com.example.app_sudamericana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)



        val botonlogin = findViewById<Button>(R.id.Btnregistrarse)
        botonlogin.setOnClickListener {
            val lanzar = Intent( this, RegistroActivity::class.java)
            startActivity(lanzar)}

        val botonregistrar = findViewById<Button>(R.id.Btnlogin)
        botonregistrar.setOnClickListener { val Abrir = Intent (this, MainActivity::class.java)
            startActivity(Abrir)}




    }
}