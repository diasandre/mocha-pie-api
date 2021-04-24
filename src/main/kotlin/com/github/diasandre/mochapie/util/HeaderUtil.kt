package com.github.diasandre.mochapie.util

import com.github.diasandre.mochapie.enums.Header.STATUS
import org.springframework.http.HttpStatus

fun String.removeWhitespaceAndSplit() = replace("\\s".toRegex(), "").split(",")

fun Map<String, String>.getStatus() = this[STATUS.value]?.toInt()?.let(HttpStatus::valueOf) ?: HttpStatus.OK