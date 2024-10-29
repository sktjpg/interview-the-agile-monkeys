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

    override fun findAll(): List<User> =
        userJpaRepository
            .findAll()
            .map(userSqlRepositoryMapper::toDomain)

    override fun createUser(user: User): User =
        user
            .let(userSqlRepositoryMapper::toEntity)
            .let(userJpaRepository::save)
            .let(userSqlRepositoryMapper::toDomain)


    override fun deleteUser(id: Long) =
        id
            .let(userJpaRepository::deleteById)

    override fun updateUser(id: Long, user: User) =
        userJpaRepository
            .updateUserById(
                id = id,
                username = user.userName,
                email = user.email,
                isAdmin = user.isAdmin,
            )

    override fun findByUserWithPassword(username: String, password: String): User? {
        return userJpaRepository
            .findByUsernameAndPassword(username, password)
            ?.let(userSqlRepositoryMapper::toDomain)
    }
}