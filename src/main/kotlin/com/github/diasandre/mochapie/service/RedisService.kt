package com.github.diasandre.mochapie.service

import com.github.diasandre.mochapie.model.StoredData
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.UUID.randomUUID
import java.util.concurrent.TimeUnit.MINUTES

@Service
class RedisService(template: RedisTemplate<String, StoredData?>) {

    private val redisClient = template.opsForValue()

    fun save(data: StoredData): UUID = randomUUID().also { uuid ->
        redisClient.set(uuid.toString(), data, 10, MINUTES)
    }

    fun get(uuid: UUID): StoredData? = redisClient[uuid.toString()]
}
