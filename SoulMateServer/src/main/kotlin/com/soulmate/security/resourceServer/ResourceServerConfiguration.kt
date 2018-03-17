package com.soulmate.security.resourceServer

import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import jdk.nashorn.internal.runtime.GlobalFunctions.anonymous
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer


@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {
    companion object {
        private val RESOURCE_ID = "soulmate_rest_api"
    }
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId(RESOURCE_ID).stateless(false)
    }

    override fun configure(http: HttpSecurity) {
        http.anonymous().disable()
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests()
                .antMatchers("/**").access("hasRole('ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }
}
