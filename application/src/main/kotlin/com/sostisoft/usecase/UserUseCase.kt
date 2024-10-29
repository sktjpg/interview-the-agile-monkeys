package com.sostisoft.usecase

import com.sostisoft.domain.User
import com.sostisoft.domain.errors.ForbiddenException
import com.sostisoft.domain.errors.NotFoundException
import com.sostisoft.domain.errors.UnauthorizedException
import com.sostisoft.ports.repository.UserRepository
import com.sostisoft.ports.security.PasswordHashGenerator
import com.sostisoft.ports.security.TokenManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UserUseCase(
    private val passwordHashGenerator: PasswordHashGenerator,
    private val userRepository: UserRepository,
    private val tokenValidatorUserCase: TokenValidatorUserCase,
) {

    private val log = LoggerFactory.getLogger(UserUseCase::class.java)

    fun getUserById(id: Long, token: String?): User =
        id
            .also { tokenValidatorUserCase.validateToken(token, true) }
            .let(userRepository::findById)
            ?: throw NotFoundException("User not found")

    fun getAllUsers(token: String?): List<User> =
        tokenValidatorUserCase.validateToken(token, true)
            .let { userRepository.findAll() }

    fun createUser(user: User, token: String?): User {
        tokenValidatorUserCase.validateToken(token, true)

        val generatedPassWord = passwordHashGenerator.generateRandomPassword()
        val hashedPassword = passwordHashGenerator.hashPassword(generatedPassWord)

        log.info(generatedPassWord)// Send email to the new user with the generated password

        return user
            .copy(password = hashedPassword)
            .let(userRepository::createUser)
    }

    fun deleteUser(id: Long, token: String?) =
        id
            .also { tokenValidatorUserCase.validateToken(token, true) }
            .let(userRepository::deleteUser)

    fun updateUser(id: Long, user: User, token: String?) =
        id
            .also { tokenValidatorUserCase.validateToken(token, true) }
            .let { userRepository.updateUser(it, user) }

}