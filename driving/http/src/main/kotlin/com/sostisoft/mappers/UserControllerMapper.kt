package com.sostisoft.mappers

import com.sostisoft.api.UserRequest
import com.sostisoft.api.UserResponse
import com.sostisoft.domain.User
import org.springframework.stereotype.Component

@Component
class UserControllerMapper {

    fun toDomain(userRequest: UserRequest): User =
        User(
            userName = userRequest.username,
            email = userRequest.email,
            isAdmin = userRequest.isAdmin,
        )

    fun toResponseEntity(user: User): UserResponse =
        UserResponse()
            .apply {
                id = user.id.toString()
                username = user.userName
                email = user.email
                isAdmin = user.isAdmin
            }

    fun toResponseEntity(user: List<User>): List<UserResponse> =
        user
            .map { toResponseEntity(it) }

}