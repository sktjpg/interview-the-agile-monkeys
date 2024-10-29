package com.sostisoft.api.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class TokenInterceptor : HandlerInterceptor {

    companion object {
        private val tokenHolder = ThreadLocal<String?>()

        fun getToken(): String? = tokenHolder.get()

        fun clearToken() {
            tokenHolder.remove()
        }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization")?.removePrefix("Bearer ")
        tokenHolder.set(token)
        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        clearToken()
    }
}
