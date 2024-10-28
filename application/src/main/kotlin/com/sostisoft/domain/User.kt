package com.sostisoft.domain

data class User(
    val id: String,
    val userName: String,
    val email: String,
    val isAdmin: Boolean
)