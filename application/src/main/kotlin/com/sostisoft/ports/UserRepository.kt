package com.sostisoft.ports

import com.sostisoft.domain.User

interface UserRepository {

    fun findById(id: String): User

}