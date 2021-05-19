package com.github.diasandre.mochapie.exceptions

import com.github.diasandre.mochapie.model.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ValidatorException::class)
    fun validatorExceptionHandler(
        exception: ValidatorException,
        response: HttpServletResponse
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(exception)
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.status)
    }
}
