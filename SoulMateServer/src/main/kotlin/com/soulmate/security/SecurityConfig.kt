package com.soulmate.security

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun userDetailsService(): UserDetailsService {
        return super.userDetailsService()
    }
}