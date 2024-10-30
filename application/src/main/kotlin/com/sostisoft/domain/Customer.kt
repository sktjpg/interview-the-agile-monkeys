package com.sostisoft.domain

import java.net.URL
import java.time.LocalDateTime

data class Customer(
    val id: Long? = null,
    val name: String,
    val surname: String,
    var photoUrl: URL? = null,
    var photo: ByteArray? = null,
    val createdBy: String? = null,
    var modifiedBy: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)