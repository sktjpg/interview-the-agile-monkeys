package com.sostisoft

import com.sostisoft.ports.security.PasswordHashGenerator
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class HashedPasswordGenerator : PasswordHashGenerator {

    override fun generateRandomPassword(): String =
        (1..15)
            .map { "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()-_=+[]{}<>?".random() }
            .joinToString("")

    override fun hashPassword(password: String): String {
        return BCryptPasswordEncoder().encode(password)
    }

    override fun isPasswordMatch(plainPassword: String, hashedPassword: String): Boolean {
        return BCryptPasswordEncoder().matches(plainPassword, hashedPassword)
    }
}