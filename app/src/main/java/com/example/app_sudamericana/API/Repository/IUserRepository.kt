package com.example.app_sudamericana.API.Repository

import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Domain.Response.UserRegisterResponse
import com.example.app_sudamericana.API.Domain.Response.UserUpdateResponse
import com.example.app_sudamericana.API.Domain.UserRegister
import com.example.app_sudamericana.API.Domain.dto.UserUpdateDto
import com.example.app_sudamericana.enviroments.Credentials
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface IUserRepository {

    @Headers("Content-Type: application/json")
    @PUT(Credentials.URL_USER + "/updatePassenger")
    fun updateUser(
        @Header("AUTHORIZATION") token: String,
        @Body data: UserUpdateDto
    ): Observable<UserUpdateResponse>

}