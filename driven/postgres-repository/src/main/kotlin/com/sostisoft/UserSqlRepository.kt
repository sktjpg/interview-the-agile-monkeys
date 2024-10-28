package com.sostisoft

import com.sostisoft.domain.User
import com.sostisoft.ports.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserSqlRepository : UserRepository {

    override fun findById(id: String): User {
        return User("1", "John Doe", "q", false)
    }
}