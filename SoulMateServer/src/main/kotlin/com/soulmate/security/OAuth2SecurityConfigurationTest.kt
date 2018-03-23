package com.soulmate.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
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
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore


@Configuration
@EnableWebSecurity
class OAuth2SecurityConfigurationTest : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var clientDetailsService: ClientDetailsService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    @Qualifier("MemberService")
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    fun globalUserDetails(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/registration/**").permitAll()
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