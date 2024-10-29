package com.sostisoft.ports.repository

import com.sostisoft.domain.User

interface UserRepository {

    fun findById(id: Long): User?

    fun findAll(): List<User>

    fun createUser(user: User): User

    fun deleteUser(id: Long)

    fun updateUser(id: Long, user: User): User

}