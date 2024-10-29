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

    override fun createUser(userRequest: @Valid UserRequest): ResponseEntity<UserResponse> =
        userRequest
            .let(userControllerMapper::toDomain)
            .let(userUseCase::createUser)
            .let(userControllerMapper::toResponseEntity)
            .let { ResponseEntity.status(201).body(it) }

    override fun getUserById(userId: String): ResponseEntity<UserResponse> =
        userId
            .toLong()
            .let(userUseCase::getUserById)
            .let(userControllerMapper::toResponseEntity)
            .let { ResponseEntity.ok(it) }

    override fun deleteUser(userId: String): ResponseEntity<Void> =
        userId
            .toLong()
            .let(userUseCase::deleteUser)
            .let { ResponseEntity.noContent().build() }

    override fun listAllUsers(): ResponseEntity<List<UserResponse>> =
        userUseCase
            .getAllUsers()
            .let(userControllerMapper::toResponseEntity)
            .let { ResponseEntity.ok(it) }

    override fun updateUser(userId: String, userRequest: UserRequest): ResponseEntity<Void> =
        userRequest
            .let(userControllerMapper::toDomain)
            .let { userUseCase.updateUser(userId.toLong(), it) }
            .let { ResponseEntity.noContent().build() }

}