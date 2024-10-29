package com.sostisoft.adapters.user

import com.sostisoft.domain.User
import org.springframework.stereotype.Component

@Component
class UserSqlRepositoryMapper {

    fun toEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            username = user.userName,
            email = user.email,
            password = user.password ?: "",
            isAdmin = user.isAdmin
        )
    }

    fun toDomain(userEntity: UserEntity): User {
        return User(
            id = userEntity.id!!,
            userName = userEntity.username,
            email = userEntity.email,
            password = userEntity.password,
            isAdmin = userEntity.isAdmin
        )
    }
}