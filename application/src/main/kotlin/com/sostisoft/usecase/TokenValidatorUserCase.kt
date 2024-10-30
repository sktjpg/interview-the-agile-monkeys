package com.sostisoft.usecase

import com.sostisoft.domain.Permission
import com.sostisoft.domain.errors.ForbiddenException
import com.sostisoft.domain.errors.UnauthorizedException
import com.sostisoft.ports.security.TokenManager
import org.springframework.stereotype.Component

@Component
class TokenValidatorUserCase(
    private val tokenManager: TokenManager
) {

    fun validateToken(token: String?, mustBeAdmin: Boolean): Permission {
        token ?: throw UnauthorizedException(AUTHENTICATION_IS_REQUIRED)

        val permission = tokenManager.validateToken(token) ?: throw UnauthorizedException(AUTHENTICATION_IS_REQUIRED)

        if (!permission.isValid) {
            throw ForbiddenException(FORBIDDEN_MESSAGE)
        }

        if (mustBeAdmin && !permission.isAdmin) {
            throw ForbiddenException(FORBIDDEN_MESSAGE)
        }

        return permission
    }

    companion object {
        private const val AUTHENTICATION_IS_REQUIRED = "Authentication is required"
        private const val FORBIDDEN_MESSAGE = "You don't have permission to access this resource"
    }
}