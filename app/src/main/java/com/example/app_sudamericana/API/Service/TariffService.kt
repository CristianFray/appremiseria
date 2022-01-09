package com.example.app_sudamericana.API.Service

import com.example.app_sudamericana.API.Domain.Authenticate
import com.example.app_sudamericana.API.Domain.RegisterReservation
import com.example.app_sudamericana.API.Domain.Response.*
import com.example.app_sudamericana.API.Domain.dto.UserUpdateDto
import com.example.app_sudamericana.API.Repository.IReservationRepository
import com.example.app_sudamericana.API.Repository.ITariffRepository
import com.example.app_sudamericana.API.RetrofitInstance
import io.reactivex.rxjava3.core.Observable

class TariffService {
    private val repository: ITariffRepository

    fun getAllTariffs(token: String): Observable<TariffResponse> {
        return repository.getAllTariffs("Bearer ${token}");
    }

    init {
        repository = RetrofitInstance.instance?.createRepository(ITariffRepository::class.java)
            ?: throw IllegalStateException()
    }

}