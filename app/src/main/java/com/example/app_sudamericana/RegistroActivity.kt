package com.example.app_sudamericana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Domain.Response.UserRegisterResponse
import com.example.app_sudamericana.API.Domain.UserRegister
import com.example.app_sudamericana.API.Service.AuthService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RegistroActivity : AppCompatActivity() {

    var authservice: AuthService = AuthService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        authservice.login(Authenticate("123456", "maximopeoficiales"))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AuthenticateResponse> {
                @Override
                override fun onSubscribe(d: Disposable) {
                    //disposables.add(d)
                }

                @Override
                override fun onNext(news: AuthenticateResponse) {
                    Toast.makeText(this@RegistroActivity, news.jwt, Toast.LENGTH_SHORT).show()
                }

                @Override
                override fun onError(e: Throwable) {
                    Toast.makeText(this@RegistroActivity, e.message, Toast.LENGTH_SHORT).show()
                }

                @Override
                override fun onComplete() {
                }
            })

        authservice.registerUser(UserRegister("fray","gdsgds@gmail.com", "cristian",2,"campos"
        ,"123456","92358895","fray"))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UserRegisterResponse> {
                @Override
                override fun onSubscribe(d: Disposable) {
                    //disposables.add(d)
                }

                @Override
                override fun onNext(news: UserRegisterResponse) {
                    Toast.makeText(this@RegistroActivity, news.email, Toast.LENGTH_SHORT).show()
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

