package com.sostisoft.api

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@RestController
@Component
class UserController : UsersApi {

    override fun createUser(userRequest: @Valid UserRequest): ResponseEntity<UserResponse> {
        return super.createUser(userRequest)
    }

    override fun getUserById(userId: String): ResponseEntity<UserResponse> {
        return super.getUserById(userId)
    }

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