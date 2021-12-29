package com.example.app_sudamericana.API.Service

import com.example.app_sudamericana.API.Domain.dto.UserUpdateDto
import com.example.app_sudamericana.API.Repository.IUserRepository
import com.example.app_sudamericana.API.RetrofitInstance
import io.reactivex.rxjava3.core.Observable

class UserService {
    private val repository: IUserRepository

    fun updateUser(token: String, data: UserUpdateDto): Observable<UserUpdateDto> {
        return repository.updateUser("Bearer ${token}", data);
    }

    init {
        repository = RetrofitInstance.instance?.createRepository(IUserRepository::class.java)
            ?: throw IllegalStateException()
    }

}