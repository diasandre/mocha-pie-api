package com.github.diasandre.mochapie.exceptions

import org.springframework.http.HttpStatus

class ValidatorException(val status: HttpStatus, message: String) : Exception(message)