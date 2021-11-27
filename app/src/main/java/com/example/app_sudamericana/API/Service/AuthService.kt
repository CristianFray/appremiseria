package com.example.app_sudamericana.API.Service

import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Domain.Response.UserRegisterResponse
import com.example.app_sudamericana.API.Domain.UserRegister
import com.example.app_sudamericana.API.Repository.IAuthRepository
import com.example.app_sudamericana.API.RetrofitInstance
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body

class AuthService {
    private val repository: IAuthRepository
   // val news: Observable<List<New>>
   //     get() = repository.getNews()


    fun registerUser(userData: UserRegister): Observable<UserRegisterResponse>{
            return repository.registerUser(userData)
    }



    fun login(userData: Authenticate): Observable<AuthenticateResponse>{
        return repository.login(userData)
    }


    init {
        repository = RetrofitInstance.instance?.createRepository(IAuthRepository::class.java)?: throw IllegalStateException()
    }

}