package com.github.diasandre.mochapie.config

import com.github.diasandre.mochapie.model.DataDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfiguration {

    @Value("\${spring.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    lateinit var redisPort: String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(redisHost, redisPort.toInt())
        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
        return JedisConnectionFactory(config, jedisClientConfiguration).apply {
            afterPropertiesSet()
        }
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, DataDTO?> = RedisTemplate<String, DataDTO?>().apply {
        setConnectionFactory(jedisConnectionFactory())
        valueSerializer = RedisJsonSerializer()
        keySerializer = StringRedisSerializer()
    }
}
