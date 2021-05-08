package com.github.diasandre.mochapie.config

import com.github.diasandre.mochapie.model.StoredData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.data.redis.serializer.RedisSerializer

class RedisJsonSerializer() : RedisSerializer<StoredData> {

    private val gson: Gson = GsonBuilder().create()

    override fun serialize(data: StoredData?): ByteArray = gson.toJson(data).toByteArray()

    override fun deserialize(bytes: ByteArray?): StoredData? =
        bytes?.let { gson.fromJson(bytes.decodeToString(), StoredData::class.java) }
}
