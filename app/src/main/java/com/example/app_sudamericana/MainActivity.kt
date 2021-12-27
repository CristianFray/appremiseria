package com.example.app_sudamericana

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Service.AuthService
import com.example.app_sudamericana.databinding.ActivityMainBinding
import com.example.app_sudamericana.fragments.HomeFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sp : SharedPreferences
  var authservice: AuthService = AuthService()

    private lateinit var userID: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnlogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userID = findViewById(R.id.TxtUser)
        password = findViewById(R.id.passwordEditText)
        btnlogin= findViewById(R.id.Btnlogin)

        //Guardar datos
        val sp = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        checkLogIn(sp)
        binding.Btnlogin.setOnClickListener { rememberUser(sp) }


        UsuarioFocusListener()
        ContraseñaFocusListener()


        val botonregistrars = findViewById<TextView>(R.id.TxtRegistrate)
        botonregistrars.setOnClickListener { val Abrir = Intent (this, RegistroActivity::class.java)
            startActivity(Abrir)}
//
//
//        val botonregistrar = findViewById<Button>(R.id.Btnlogin)
//        botonregistrar.setOnClickListener { val Abrir = Intent (this, HomeActivity::class.java)
//            startActivity(Abrir)}

     //   btnlogin.setOnClickListener ({createlogin()})

      //binding.Btnlogin.setOnClickListener { submitLogin() }
//==================================================================================================
    }
    private fun rememberUser(sp : SharedPreferences){
        // Recuperamos el contenido de los textField
        val email = binding.usuarioContainer.editText?.text.toString()
        val password = binding.passwordContainer.editText?.text.toString()

        // Verificamos si los campos no son vacíos
        if (email.isNotEmpty() && password.isNotEmpty()){
            val checkBox = binding.checkBox
            if (checkBox.isChecked){
                with(sp.edit()){

                    putString("email", email)
                    putString("password", password)
                    putString("active", "true")
                    putString("remember", "true")
                    createlogin()
                    apply()

                }
            }else{
                with(sp.edit()){
                    putString("active", "true")
                    putString("remember", "false")

                    apply()
                }
            }
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }else{
            // En caso los datos no estén completos mostramos un Toast
            submitLogin()
        }
    }


    private fun checkLogIn(sp : SharedPreferences){
        if (sp.getString("active","") == "true"){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }else{
            if (sp.getString("remember","") == "true"){
                binding.usuarioContainer.editText?.setText(sp.getString("email",""))
                binding.passwordContainer.editText?.setText(sp.getString("password",""))
            }
        }
    }

















    private fun submitLogin() {
        val validarUsuario = binding.usuarioContainer.helperText == null
        val validarContraseña = binding.passwordContainer.helperText == null

        binding.usuarioContainer.helperText = validarUsuario()
        binding.passwordContainer.helperText = validarContraseña()


    }

    //validar Usuario
    private fun UsuarioFocusListener() {
        binding.TxtUser.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.usuarioContainer.helperText = validarUsuario()
            }
        }
    }
    private fun validarUsuario(): String? {
        val nombreText = binding.TxtUser.text.toString()
        if(nombreText.length<5)
        {
            return "Escribe tu Usuario"
        }
        return null
    }


    //validar Contraseña
    private fun ContraseñaFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validarContraseña()
            }
        }
    }
    private fun validarContraseña(): String? {
        val nombreText = binding.passwordEditText.text.toString()
        if(nombreText.length<7)
        {
            return "Escribe tu Contraseña"
        }
        return null
    }



    private fun createlogin(){
        authservice.login(Authenticate(userID.text.toString(), password.text.toString()))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AuthenticateResponse> {
                @Override
                override fun onSubscribe(d: Disposable) {
                    //disposables.add(d)
                }

                @Override
                override fun onNext(data: AuthenticateResponse) {
                    Toast.makeText(this@MainActivity, data.jwt, Toast.LENGTH_SHORT).show()

                }

                @Override
                override fun onError(e: Throwable) {

                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }

                @Override
                override fun onComplete() {
                }
            })

//        val botonlogin = findViewById<Button>(R.id.Btnlogin)
//        botonlogin.setOnClickListener {
//            val lanzar = Intent( this, HomeActivity::class.java)
//            startActivity(lanzar)}


        val Textregistrar = findViewById<TextView>(R.id.TxtRegistrate)
        Textregistrar.setOnClickListener {
            val lanzar = Intent ( this, RegistroActivity::class.java)
            startActivity(lanzar)}
    }
}