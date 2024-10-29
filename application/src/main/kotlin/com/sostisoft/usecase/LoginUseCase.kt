package com.sostisoft.usecase

import com.sostisoft.domain.errors.UserLoginException
import com.sostisoft.ports.repository.UserRepository
import com.sostisoft.ports.security.PasswordHashGenerator
import com.sostisoft.ports.security.TokenManager
import org.springframework.stereotype.Component

@Component
class LoginUseCase(
    private val passwordHashGenerator: PasswordHashGenerator,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
) {

    fun login(username: String, password: String): String =
        password
            .let(passwordHashGenerator::hashPassword)
            .let { userRepository.findByUserWithPassword(username, it) }
            ?.let(tokenManager::generateToken)
            ?: throw UserLoginException("Incorrect login")
}