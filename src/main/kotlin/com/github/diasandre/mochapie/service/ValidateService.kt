package com.github.diasandre.mochapie.service

import com.github.diasandre.mochapie.enums.Header.AUTH_REQUIRED
import com.github.diasandre.mochapie.enums.Header.IS_REQUIRED
import com.github.diasandre.mochapie.exceptions.ValidatorException
import com.github.diasandre.mochapie.util.removeWhitespaceAndSplit
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.stereotype.Service

// TODO improve isValidRequiredHeaders to show what header is missing

@Service
class ValidateService {

    fun isValidHeader(headers: Map<String, String>): Nothing? = when {
        !headers.isValidAuth() -> throw ValidatorException(UNAUTHORIZED, "auth is required")
        !headers.isValidRequiredHeaders() -> throw ValidatorException(
            BAD_REQUEST,
            "required header is missing"
        )
        else -> null
    }

    private fun Map<String, String>.isValidAuth(): Boolean {
        val authRequired = this[AUTH_REQUIRED.value]?.takeIf(String::isNotEmpty)?.let(String::toBoolean) ?: false
        return if (authRequired) this["authorization"] != null else !authRequired
    }

    private fun Map<String, String>.isValidRequiredHeaders(): Boolean {
        val requiredHeaders: List<String> = this[IS_REQUIRED.value]
            ?.takeIf(String::isNotEmpty)
            ?.let(String::removeWhitespaceAndSplit)
            ?: emptyList()

        return requiredHeaders.all { this[it] != null }
    }
}
