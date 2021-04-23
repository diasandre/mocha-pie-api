package com.github.diasandre.mochapie.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.UUID.randomUUID
import java.util.concurrent.TimeUnit.MINUTES

@Service
class RedisService(stringRedisTemplate: RedisTemplate<String, String>) {

    private val redisClient = stringRedisTemplate.opsForValue()

    fun save(value: String): UUID = randomUUID().also { uuid ->
        redisClient.set(uuid.toString(), value, 10, MINUTES)
    }

    fun get(uuid: UUID) = redisClient[uuid.toString()]
}