package com.sostisoft.usecase

import com.sostisoft.domain.User
import com.sostisoft.ports.UserRepository
import org.springframework.stereotype.Component

@Component
class UserUseCase(private val userRepository: UserRepository) {

    fun getUserById(id: String): User {
        return userRepository.findById(id)
    }
}