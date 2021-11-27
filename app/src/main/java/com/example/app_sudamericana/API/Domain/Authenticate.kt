package com.example.app_sudamericana.API.Domain
import com.google.gson.annotations.SerializedName


data class Authenticate(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)