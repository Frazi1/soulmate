package com.soulmate.services.helpers

import org.codehaus.jackson.map.ObjectMapper
import org.springframework.stereotype.Component

@Component
class JsonHelper(val mapper: ObjectMapper) {
    fun <T> toJson(item: T): String = mapper.writeValueAsString(item)
}