package com.sostisoft.mappers

import com.sostisoft.api.UserResponse
import com.sostisoft.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class UserControllerMapper {

    fun toResponseEntity(user: User): ResponseEntity<UserResponse> =
        UserResponse()
            .apply {
                id = user.id
                username = user.userName
                email = user.email
                isAdmin = user.isAdmin
            }
            .let { ResponseEntity.ok(it) }

}