package com.sostisoft.api

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@RestController
@Component
class UserController : UsersApi {

    override fun _createUser(userRequest: @Valid UserRequest?): ResponseEntity<UserResponse?>? {
        return super._createUser(userRequest)
    }

    override fun _getUserById(userId: String?): ResponseEntity<UserResponse?>? {
        return super._getUserById(userId)
    }

    override fun _deleteUser(userId: String?): ResponseEntity<Void?>? {
        return super._deleteUser(userId)
    }

    override fun _listAllUsers(): ResponseEntity<List<UserResponse?>?>? {
        return super._listAllUsers()
    }

    override fun _updateUser(
        userId: String?,
        userRequest: @Valid UserRequest?
    ): ResponseEntity<Void?>? {
        return super._updateUser(userId, userRequest)
    }
}