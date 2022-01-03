package com.example.app_sudamericana.API.Repository

import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Domain.Response.ReservationResponse
import com.example.app_sudamericana.API.Domain.Response.UserRegisterResponse
import com.example.app_sudamericana.API.Domain.Response.UserUpdateResponse
import com.example.app_sudamericana.API.Domain.UserRegister
import com.example.app_sudamericana.API.Domain.UserUpdate
import com.example.app_sudamericana.enviroments.Credentials
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface IAuthRepository {

    @Headers("Content-Type: application/json")
    @POST(Credentials.URL_AUTH+"/register")
    fun registerUser(@Body userData: UserRegister): Observable<UserRegisterResponse>

    @Headers("Content-Type: application/json")
    @POST(Credentials.URL_AUTH+"/authenticate")
    fun login(@Body userData: Authenticate): Observable<AuthenticateResponse>




}