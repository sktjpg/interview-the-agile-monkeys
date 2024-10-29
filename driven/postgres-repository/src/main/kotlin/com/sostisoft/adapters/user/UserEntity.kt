package com.sostisoft.adapters.user

import jakarta.persistence.*

@Entity
@Table(name = "\"user\"")
data class UserEntity(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "username", nullable = false, unique = true)
    val username: String = "",

    @Column(name = "password")
    val password: String = "",

    @Column(name = "email", nullable = false)
    val email: String = "",

    @Column(name = "is_admin", nullable = false)
    val isAdmin: Boolean = false
)

