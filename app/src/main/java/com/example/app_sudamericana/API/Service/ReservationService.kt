package com.example.app_sudamericana.API.Service

import com.example.app_sudamericana.API.Domain.Response.ReservationResponse
import com.example.app_sudamericana.API.Repository.IReservationRepository
import com.example.app_sudamericana.API.RetrofitInstance
import io.reactivex.rxjava3.core.Observable

class ReservationService {
    private val repository: IReservationRepository

    fun getAllReservations(token: String): Observable<ReservationResponse> {
        return repository.getAllReservations("Bearer ${token}");
    }

    init {
        repository = RetrofitInstance.instance?.createRepository(IReservationRepository::class.java)
            ?: throw IllegalStateException()
    }

}