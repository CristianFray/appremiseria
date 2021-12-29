package com.example.app_sudamericana

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_sudamericana.API.Service.AuthService
import com.example.app_sudamericana.API.Service.UserService
import com.example.app_sudamericana.enviroments.Credentials

class EditarPerfilActivity : AppCompatActivity() {

    var userService: UserService = UserService()
    private lateinit var spInstance: SharedPreferences;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)
        this.spInstance = getSharedPreferences(Credentials.NAME_PREFERENCES, Context.MODE_PRIVATE)


    }



}