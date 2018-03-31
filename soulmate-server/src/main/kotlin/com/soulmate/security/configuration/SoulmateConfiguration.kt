package com.soulmate.security.configuration

import com.soulmate.security.SimplePasswordEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ["com.soulmate"])
class SoulmateConfiguration {
    @Bean
    fun passwordEncoder(): PasswordEncoder = SimplePasswordEncoder()

}