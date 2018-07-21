package com.soulmate.config

import org.codehaus.jackson.map.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiConfig {

    @Bean
    fun mapper(): ObjectMapper = ObjectMapper()
}