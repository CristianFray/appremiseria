package com.example.app_sudamericana.API.Domain.Response

data class Role(
    val dateCreated: String,
    val dateUpdated: String,
    val description: String,
    val idRole: Int,
    val permissions: List<Permission>
)