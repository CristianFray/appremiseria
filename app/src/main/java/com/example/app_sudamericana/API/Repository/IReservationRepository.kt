package com.example.app_sudamericana.API.Repository

import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.RegisterReservation
import com.example.app_sudamericana.API.Domain.Response.AuthenticateResponse
import com.example.app_sudamericana.API.Domain.Response.RegisterReservationResponse
import com.example.app_sudamericana.API.Domain.Response.ReservationResponse
import com.example.app_sudamericana.API.Domain.Response.UserUpdateResponse
import com.example.app_sudamericana.API.Domain.dto.UserUpdateDto
import com.example.app_sudamericana.enviroments.Credentials
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface IReservationRepository {

    @Headers("Content-Type: application/json")
    @GET(Credentials.URL_RESERVATION + "/all")
    fun getAllReservations(@Header("AUTHORIZATION") token: String): Observable<ReservationResponse>


    @Headers("Content-Type: application/json")
    @POST(Credentials.URL_RESERVATION)
    fun reservation(
        @Header ( "AUTHORIZATION")token: String,
        @Body userData: RegisterReservation):
            Observable<RegisterReservationResponse>
}