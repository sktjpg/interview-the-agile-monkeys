package com.sostisoft.errors

import com.sostisoft.domain.errors.ExpiredTokenException
import com.sostisoft.domain.errors.ForbiddenException
import com.sostisoft.domain.errors.NotFoundException
import com.sostisoft.domain.errors.UnauthorizedException
import com.sostisoft.domain.errors.UserLoginException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(
        ex: ResponseStatusException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.reason ?: "Unknown error",
            status = ex.statusCode.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, ex.statusCode)
    }

    @ExceptionHandler(UserLoginException::class)
    fun handleResponseStatusException(
        ex: UserLoginException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message!!,
            status = HttpStatus.BAD_REQUEST.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ExpiredTokenException::class)
    fun handleResponseStatusException(
        ex: ExpiredTokenException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message!!,
            status = HttpStatus.UNAUTHORIZED.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleResponseStatusException(
        ex: UnauthorizedException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message!!,
            status = HttpStatus.UNAUTHORIZED.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleResponseStatusException(
        ex: ForbiddenException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message!!,
            status = HttpStatus.FORBIDDEN.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleResponseStatusException(
        ex: NotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message!!,
            status = HttpStatus.NOT_FOUND.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "An error occurred",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            path = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

data class ErrorResponse(
    val message: String,
    val status: Int,
    val path: String
)
