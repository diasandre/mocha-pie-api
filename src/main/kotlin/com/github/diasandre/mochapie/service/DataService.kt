package com.github.diasandre.mochapie.service

import com.github.diasandre.mochapie.exceptions.ValidatorException
import com.github.diasandre.mochapie.model.DataDTO
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
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

    fun isValid(data: DataDTO): Boolean = when {
        data.values.isEmpty() -> throw ValidatorException(BAD_REQUEST, "Add some data, pls")
        data.hasRepeatedStatus() -> throw ValidatorException(BAD_REQUEST, "Remove repeated status, pls")
        else -> true
    }

    private fun DataDTO.hasRepeatedStatus() = values.map { it.status.value }.toSet().count() != values.size

    private fun DataDTO.getByStatus(status: HttpStatus) =
        values.firstOrNull { it.status.value == status.value() } ?: throw ValidatorException(
            NOT_FOUND, "response body for $status not found"
        )
}
