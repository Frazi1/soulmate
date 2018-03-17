package com.soulmate.security

import org.springframework.security.oauth2.provider.approval.TokenApprovalStore
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.authentication.AuthenticationManager
import jdk.nashorn.internal.runtime.GlobalFunctions.anonymous
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.logging.Logger


@Configuration
@EnableWebSecurity
class OAuth2SecurityConfigurationTest : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var clientDetailsService: ClientDetailsService

    @Autowired
    fun globalUserDetails(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .passwordEncoder(object : PasswordEncoder {
                    override fun encode(seq: CharSequence): String = seq.toString()

                    override fun matches(seq: CharSequence, pass: String): Boolean = encode(seq) == pass
                })
                .withUser("bill").password("abc123").roles("ADMIN").and()
                .withUser("bob").password("abc123").roles("USER")

    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
    }

    @Bean(name = [(BeanIds.AUTHENTICATION_MANAGER)])
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }

    @Bean
    @Autowired
    fun userApprovalHandler(tokenStore: TokenStore): TokenStoreUserApprovalHandler {
        val handler = TokenStoreUserApprovalHandler()
        handler.setTokenStore(tokenStore)
        handler.setRequestFactory(DefaultOAuth2RequestFactory(clientDetailsService))
        handler.setClientDetailsService(clientDetailsService)
        return handler
    }

    @Bean
    @Autowired
    fun approvalStore(tokenStore: TokenStore): ApprovalStore {
        val store = TokenApprovalStore()
        store.setTokenStore(tokenStore)
        return store
    }
}