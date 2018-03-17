package com.soulmate.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
class MethodSecurityConfig : GlobalMethodSecurityConfiguration() {

//    @Autowired
//    private lateinit var securityConfig: OAuth2SecurityConfigurationTest;

//    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
//        return OAuth2MethodSecurityExpressionHandler();
//    }
}