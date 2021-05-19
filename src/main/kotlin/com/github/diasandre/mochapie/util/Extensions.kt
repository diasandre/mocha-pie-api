package com.github.diasandre.mochapie.util

import com.github.diasandre.mochapie.enums.Header.STATUS
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletResponse

fun String.removeWhitespaceAndSplit() = replace("\\s".toRegex(), "").split(",")

fun Map<String, String>.getStatus() = this[STATUS.value]?.toInt()?.let(HttpStatus::valueOf) ?: HttpStatus.OK

fun HttpServletResponse.setResponse(status: HttpStatus) {
    this.status = status.value()
}
