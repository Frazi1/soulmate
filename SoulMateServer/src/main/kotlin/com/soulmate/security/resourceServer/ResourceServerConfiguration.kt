package com.soulmate.security.resourceServer

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler


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
        http/*.httpBasic()
                .and().anonymous().disable()*/
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }
}
