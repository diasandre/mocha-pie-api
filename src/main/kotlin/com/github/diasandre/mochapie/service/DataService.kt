package com.github.diasandre.mochapie.service

import com.github.diasandre.mochapie.exceptions.ValidatorException
import com.github.diasandre.mochapie.model.StoredData
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class DataService(private val redisService: RedisService) {

    fun save(data: StoredData?): UUID? = data?.takeIf(::isValid)?.let(redisService::save)
    fun get(uuid: UUID): StoredData? = redisService.get(uuid)
    fun getByStatus(uuid: UUID, status: HttpStatus): String? =
        redisService.get(uuid).takeIf(Objects::nonNull)?.getByStatus(status)

    //TODO improve isValid
    fun isValid(data: StoredData): Boolean = when {
        data.values.isEmpty() -> error("value is empty")
        else -> true
    }

    fun StoredData.getByStatus(status: HttpStatus) = values[status.value().toString()] ?: throw ValidatorException(
        NOT_FOUND, "response body for $status not found"
    )
}