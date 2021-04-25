package com.github.diasandre.mochapie.model

import com.github.diasandre.mochapie.exceptions.ValidatorException
import org.springframework.http.HttpStatus

data class ErrorResponse(
    val reason: String,
    val status: HttpStatus,
    val code: Int = status.value()
) {
    constructor(exception: ValidatorException) : this(
        exception.localizedMessage,
        exception.status
    )
}