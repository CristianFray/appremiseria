package com.example.app_sudamericana

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Domain.Response.UserRegisterResponse
import com.example.app_sudamericana.API.Domain.Response.UserUpdateResponse
import com.example.app_sudamericana.API.Domain.UserRegister
import com.example.app_sudamericana.API.Domain.UserUpdate
import com.example.app_sudamericana.API.Domain.dto.UserUpdateDto
import com.example.app_sudamericana.API.Service.AuthService
import com.example.app_sudamericana.API.Service.UserService
import com.example.app_sudamericana.databinding.ActivityEditarPerfilBinding
import com.example.app_sudamericana.databinding.ActivityRegistroBinding
import com.example.app_sudamericana.enviroments.Credentials
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EditarPerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarPerfilBinding
    var userService: UserService = UserService()
    val disposables: CompositeDisposable = CompositeDisposable()

    private lateinit var spInstance: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnActualizar.setOnClickListener({ updateUser()})


        this.spInstance = getSharedPreferences(Credentials.NAME_PREFERENCES, Context.MODE_PRIVATE)

        cargarDatos()


    }


private fun cargarDatos(){
    val nombre = this.spInstance.getString(Credentials.USER_FIRSTNAME, "");
    val apellido = this.spInstance.getString(Credentials.USER_LASTNAME, "");
    val direccion = this.spInstance.getString(Credentials.USER_ADDRESS, "");
    val telefono = this.spInstance.getString(Credentials.USER_TELEFONO, "");
    val pasword = this.spInstance.getString(Credentials.USER_PASSWORD, "");
    binding.TxtNombre.setText(nombre)
    binding.TxtApellido.setText(apellido)
    binding.TxtDireccion.setText(direccion)
    binding.TxtTelefono.setText(telefono)
    binding.TxtPassword.setText(pasword)

}


    private fun updateUser() {
        val token = this.spInstance.getString(Credentials.TOKEN_JWT, "fff");
        val userID = this.spInstance.getString(Credentials.USER_ID, 1.toString());
        val email = this.spInstance.getString(Credentials.USER_EMAIL, "fff");
        val user = this.spInstance.getString(Credentials.USER_USERNAME, "fff");
        if (token  != null && email!= null && userID!= null && user!= null) {
            var userUpdate = UserUpdateDto(
                true,
              binding.TxtDireccion.getText().toString(),
                email,
                binding.TxtNombre.getText().toString(),
                userID.toString().toInt(),
                binding.TxtApellido.getText().toString(),
                binding.TxtPassword.getText().toString(),
                binding.TxtTelefono.getText().toString(),
                user,




            );
            userService.updateUser(token, userUpdate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<UserUpdateResponse> {


                    override fun onNext(t: UserUpdateResponse) {
                        Toast.makeText(
                            this@EditarPerfilActivity,
                            "Actualizado Correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                        this@EditarPerfilActivity.finish()

                    }

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(
                            this@EditarPerfilActivity,
                            e.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onComplete() {
                       disposables.clear()
                    }
                })


        }
    }

}

