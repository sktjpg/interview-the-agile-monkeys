package com.sostisoft.domain

data class User(
    val id: Long? = null,
    val userName: String,
    val email: String,
    val isAdmin: Boolean,
    val password: String? = null,
)