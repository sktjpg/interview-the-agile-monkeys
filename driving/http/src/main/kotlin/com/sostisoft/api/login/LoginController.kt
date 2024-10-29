package com.sostisoft.api.login

import com.sostisoft.api.LoginApi
import com.sostisoft.api.LoginRequest
import com.sostisoft.api.LoginResponse
import com.sostisoft.usecase.LoginUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
class LoginController(private val loginUseCase: LoginUseCase) : LoginApi {

    override fun loginUser(loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val token = loginUseCase.login(loginRequest.username, loginRequest.password)
        val loginResponse = LoginResponse().apply { accessToken = token }
        return ResponseEntity.ok(loginResponse)
    }
}