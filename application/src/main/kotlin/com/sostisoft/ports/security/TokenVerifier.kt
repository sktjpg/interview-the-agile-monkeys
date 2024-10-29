package com.sostisoft.ports.security

interface TokenVerifier {

    fun canAccessResource(token: String, isAdmin: Boolean): Boolean

}