package com.sostisoft.domain

data class User(
    val id: Long,
    val userName: String,
    val email: String,
    val isAdmin: Boolean,
    val password: String
)