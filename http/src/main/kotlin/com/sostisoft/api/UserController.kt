package com.sostisoft.api

import com.sostisoft.mappers.UserControllerMapper
import com.sostisoft.usecase.UserUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@RestController
@Component
class UserController(
    private val userUseCase: UserUseCase,
    private val userControllerMapper: UserControllerMapper
) : UsersApi {

    override fun createUser(userRequest: @Valid UserRequest): ResponseEntity<UserResponse> {
        return super.createUser(userRequest)
    }

    override fun getUserById(userId: String): ResponseEntity<UserResponse> =
        userId
            .let(userUseCase::getUserById)
            .let(userControllerMapper::toResponseEntity)

    override fun deleteUser(userId: String): ResponseEntity<Void> {
        return super.deleteUser(userId)
    }

    override fun listAllUsers(): ResponseEntity<List<UserResponse>> {
        return super.listAllUsers()
    }

    override fun updateUser(userId: String, userRequest: UserRequest): ResponseEntity<Void> {
        return super.updateUser(userId, userRequest)
    }
}