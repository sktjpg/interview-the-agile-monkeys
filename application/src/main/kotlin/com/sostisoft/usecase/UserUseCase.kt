package com.sostisoft.usecase

import com.sostisoft.domain.User
import com.sostisoft.domain.errors.NotFoundException
import com.sostisoft.ports.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserUseCase(private val userRepository: UserRepository) {

    fun getUserById(id: Long): User =
        id
            .let(userRepository::findById)
            ?: throw NotFoundException("User not found")

    fun getAllUsers(): List<User> =
        userRepository.findAll()

    fun createUser(user: User): User =
        user
            .let(userRepository::createUser)

    fun deleteUser(id: Long) =
        id
            .let(userRepository::deleteUser)

    fun updateUser(id: Long, user: User) =
        id
            .let { userRepository.updateUser(it, user) }

}