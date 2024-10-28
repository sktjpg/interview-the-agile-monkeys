package com.sostisoft.ports.repository

import com.sostisoft.domain.User

interface UserRepository {

    fun findById(id: String): User

}