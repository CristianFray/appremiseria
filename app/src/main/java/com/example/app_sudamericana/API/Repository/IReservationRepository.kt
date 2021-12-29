package com.example.app_sudamericana.API.Repository

import com.example.app_sudamericana.API.Domain.Response.ReservationResponse
import com.example.app_sudamericana.enviroments.Credentials
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface IReservationRepository {

    @Headers("Content-Type: application/json")
    @GET(Credentials.URL_RESERVATION + "/all")
    fun getAllReservations(@Header("AUTHORIZATION") token: String): Observable<ReservationResponse>

}