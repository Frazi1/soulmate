package com.soulmate.services.helpers

import org.codehaus.jackson.map.ObjectMapper
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class JsonHelper(val mapper: ObjectMapper) {
    fun <T> toJson(item: T): String = mapper.writeValueAsString(item)
    fun <T : Any> fromJson(json: String, clazz: KClass<T>): T = mapper.readValue(json, clazz.java)
}