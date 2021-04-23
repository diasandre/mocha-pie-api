package com.github.diasandre.mochapie.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class DataService(private val redisService: RedisService) {

    fun save(value: String): UUID? = value.takeIf(::isValid)?.let(redisService::save)
    fun get(uuid: UUID): String? = redisService.get(uuid)

    fun isValid(value: String): Boolean = when {
        value.isEmpty() || value.isBlank() -> error("value is empty")
        else -> true
    }
}