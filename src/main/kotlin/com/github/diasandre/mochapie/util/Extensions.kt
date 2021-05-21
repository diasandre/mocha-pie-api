package com.github.diasandre.mochapie.util

import com.github.diasandre.mochapie.enums.Header.STATUS
import com.github.diasandre.mochapie.exceptions.ValidatorException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.OK
import java.util.UUID
import javax.servlet.http.HttpServletResponse

fun String.removeWhitespaceAndSplit() = replace("\\s".toRegex(), "").split(",")

fun Map<String, String>.getStatus() = this[STATUS.value]?.toInt()?.let(HttpStatus::valueOf) ?: OK

fun HttpServletResponse.setResponse(status: HttpStatus) {
    this.status = status.value()
}

fun String.toUUID() = runCatching {
    UUID.fromString(this)
}.getOrNull() ?: throw ValidatorException(INTERNAL_SERVER_ERROR, "Invalid UUID")
