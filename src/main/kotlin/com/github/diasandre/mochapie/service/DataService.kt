package com.github.diasandre.mochapie.service

import com.github.diasandre.mochapie.exceptions.ValidatorException
import com.github.diasandre.mochapie.model.DataDTO
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Service
import java.util.Objects
import java.util.UUID

@Service
class DataService(private val redisService: RedisService) {

    fun save(data: DataDTO?): UUID? = data?.takeIf(::isValid)?.let(redisService::save)
    fun get(uuid: UUID): DataDTO? = redisService.get(uuid)
    fun getByStatus(uuid: UUID, status: HttpStatus): String? =
        redisService.get(uuid).takeIf(Objects::nonNull)?.getByStatus(status)?.responseBody

    // TODO improve isValid
    fun isValid(data: DataDTO): Boolean = when {
        data.values.isEmpty() -> error("value is empty")
        else -> true
    }

    fun DataDTO.getByStatus(status: HttpStatus) = values.firstOrNull { it.status.value == status.value() } ?: throw ValidatorException(
        NOT_FOUND, "response body for $status not found"
    )
}
