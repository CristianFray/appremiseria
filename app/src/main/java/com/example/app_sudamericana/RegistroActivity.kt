package com.example.app_sudamericana


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.app_sudamericana.API.Domain.Response.UserRegisterResponse
import com.example.app_sudamericana.API.Domain.UserRegister
import com.example.app_sudamericana.API.Service.AuthService
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    var authservice: AuthService = AuthService()


    private lateinit var address: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var btnRegisterUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        address = findViewById(R.id.TxtDireccion)
        email = findViewById(R.id.TxtCorreo)
        firstName = findViewById(R.id.TxtNombre)
        lastName = findViewById(R.id.TxtApellido)
        password = findViewById(R.id.TxtPassword)
        phone    = findViewById(R.id.TxtTelefono)
        username = findViewById(R.id.TxtUsuario)
        btnRegisterUser = findViewById(R.id.BtnRegisterUser)


       btnRegisterUser.setOnClickListener ({createUser()})
/*//
//        authservice.login(Authenticate("123456", "maximopeoficiales"))
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<AuthenticateResponse> {
//                @Override
//                override fun onSubscribe(d: Disposable) {
//                    //disposables.add(d)
//                }
//
//                @Override
//                override fun onNext(news: AuthenticateResponse) {
//                    Toast.makeText(this@RegistroActivity, news.jwt, Toast.LENGTH_SHORT).show()
//                }
//
//                @Override
//                override fun onError(e: Throwable) {
//                    Toast.makeText(this@RegistroActivity, e.message, Toast.LENGTH_SHORT).show()
//                }
//
//                @Override
//                override fun onComplete() {
//                }
//            })*/



    }

    private fun createUser(){


        authservice.registerUser(UserRegister(address.text.toString(), email.text.toString(), firstName.text.toString(),1,lastName.text.toString()
            ,password.text.toString(),phone.text.toString(), username.text.toString()))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UserRegisterResponse> {
                @Override
                override fun onSubscribe(d: Disposable) {
                    //disposables.add(d)
                }

                @Override
                override fun onNext(news: UserRegisterResponse) {
//
                }

                @Override
                override fun onError(e: Throwable) {
                    Toast.makeText(this@RegistroActivity, e.message, Toast.LENGTH_SHORT).show()
                }

                @Override
                override fun onComplete() {
                }
            })

    }

}


