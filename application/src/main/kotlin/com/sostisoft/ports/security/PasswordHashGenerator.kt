package com.sostisoft.ports.security

interface PasswordHashGenerator {

    fun generateRandomPassword(): String

    fun hashPassword(password: String): String

    fun isPasswordMatch(plainPassword: String, hashedPassword: String): Boolean
}