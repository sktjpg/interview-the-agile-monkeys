package com.sostisoft.usecase

import com.sostisoft.ports.repository.UserRepository
import com.sostisoft.ports.security.PasswordHashGenerator
import org.springframework.stereotype.Component

@Component
class LoginUseCase(
    private val passwordHashGenerator: PasswordHashGenerator,
    private val userRepository: UserRepository,
    private val token
) {

    fun login(username: String, password: String): String {
        val hashedPassword = passwordHashGenerator.hashPassword(password)
        val user = userRepository.findByUserWithPassword(username, hashedPassword)

        return
    }

}