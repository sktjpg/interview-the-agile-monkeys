package com.sostisoft.adapters.user

import com.sostisoft.domain.User
import com.sostisoft.ports.repository.UserRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class UserSqlRepository(
    private val userJpaRepository: UserJpaRepository,
    private val userSqlRepositoryMapper: UserSqlRepositoryMapper,
    ) : UserRepository {

    override fun findById(id: Long): User? =
        id
            .let(userJpaRepository::findById)
            .getOrNull()
            ?.let(userSqlRepositoryMapper::toDomain)
}