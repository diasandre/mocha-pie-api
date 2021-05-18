package com.github.diasandre.mochapie.service

import com.github.diasandre.mochapie.model.DataDTO
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.UUID.randomUUID
import java.util.concurrent.TimeUnit.MINUTES

@Service
class RedisService(template: RedisTemplate<String, DataDTO?>) {

    private val redisClient = template.opsForValue()

    fun save(data: DataDTO): UUID = randomUUID().also { uuid ->
        redisClient.set(uuid.toString(), data.copy(uuid = uuid), 10, MINUTES)
    }

    fun get(uuid: UUID): DataDTO? = redisClient[uuid.toString()]
}
