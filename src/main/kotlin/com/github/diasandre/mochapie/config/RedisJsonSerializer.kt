package com.github.diasandre.mochapie.config

import com.github.diasandre.mochapie.model.DataDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.data.redis.serializer.RedisSerializer

class RedisJsonSerializer() : RedisSerializer<DataDTO> {

    private val gson: Gson = GsonBuilder().create()

    override fun serialize(data: DataDTO?): ByteArray = gson.toJson(data).toByteArray()

    override fun deserialize(bytes: ByteArray?): DataDTO? =
        bytes?.let { gson.fromJson(bytes.decodeToString(), DataDTO::class.java) }
}
