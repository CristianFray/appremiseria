package com.example.app_sudamericana.API.Repository

import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.RegisterReservation
import com.example.app_sudamericana.API.Domain.Response.*
import com.example.app_sudamericana.API.Domain.dto.UserUpdateDto
import com.example.app_sudamericana.enviroments.Credentials
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ITariffRepository {

    @Headers("Content-Type: application/json")
    @GET(Credentials.URL_TARIFF + "/all")
    fun getAllTariffs(@Header("AUTHORIZATION") token: String): Observable<TariffResponse>



}