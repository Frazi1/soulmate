package com.soulmate.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun userDetailsService(): UserDetailsService {
        return userDetailsService
    }

    override fun configure(http: HttpSecurity) {
//        http.cors().and().authorizeRequests().anyRequest().authenticated()
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/hello", "/").permitAll()
                .anyRequest().authenticated()
    }

    @Bean(name = [(BeanIds.AUTHENTICATION_MANAGER)])
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(object : PasswordEncoder {
                    override fun encode(seq: CharSequence): String = seq.toString()

                    override fun matches(seq: CharSequence, pass: String): Boolean = encode(seq) == pass
                })
    }
}