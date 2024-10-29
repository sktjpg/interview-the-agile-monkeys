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

    fun login(username: String, password: String): String {
        val user = userRepository.findByUserName(username)
        val isCorrect = passwordHashGenerator.isPasswordMatch(password, user!!.password!!)
        return if (isCorrect) tokenManager.generateToken(user) else throw UserLoginException("Incorrect login")
    }
       //username
       //    .let(userRepository::findByUserName)
       //    .takeIf { it != null && passwordHashGenerator.isPasswordMatch(password, it.password!!) }
       //    ?.let(tokenManager::generateToken)
       //    ?: throw UserLoginException("Incorrect login")
}