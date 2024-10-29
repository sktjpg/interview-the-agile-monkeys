package com.sostisoft.adapters.user

import com.sostisoft.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    @Modifying
    @Query("UPDATE UserEntity u SET u.username = :username, u.email = :email, u.isAdmin = :isAdmin WHERE u.id = :id")
    fun updateUserById(
        @Param("id") id: Long,
        @Param("username") username: String,
        @Param("email") email: String,
        @Param("isAdmin") isAdmin: Boolean
    ): User?

    fun findByUsername(username: String): UserEntity?
}