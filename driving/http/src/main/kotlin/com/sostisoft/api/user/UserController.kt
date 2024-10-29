package com.sostisoft.api.user

import com.sostisoft.api.UserRequest
import com.sostisoft.api.UserResponse
import com.sostisoft.api.UsersApi
import com.sostisoft.api.interceptor.TokenInterceptor
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
            .let { userUseCase.createUser(it, TokenInterceptor.getToken()) }
            .let(userControllerMapper::toResponseEntity)
            .let { ResponseEntity.status(201).body(it) }

    override fun getUserById(userId: String): ResponseEntity<UserResponse> =
        userId
            .toLong()
            .let { userUseCase.getUserById(it, TokenInterceptor.getToken()) }
            .let(userControllerMapper::toResponseEntity)
            .let { ResponseEntity.ok(it) }

    override fun deleteUser(userId: String): ResponseEntity<Void> =
        userId
            .toLong()
            .let {userUseCase.deleteUser(it, TokenInterceptor.getToken())}
            .let { ResponseEntity.noContent().build() }

    override fun listAllUsers(): ResponseEntity<List<UserResponse>> =
        userUseCase
            .getAllUsers(TokenInterceptor.getToken())
            .let(userControllerMapper::toResponseEntity)
            .let { ResponseEntity.ok(it) }

    override fun updateUser(userId: String, userRequest: UserRequest): ResponseEntity<Void> =
        userRequest
            .let(userControllerMapper::toDomain)
            .let { userUseCase.updateUser(userId.toLong(), it, TokenInterceptor.getToken()) }
            .let { ResponseEntity.noContent().build() }

}