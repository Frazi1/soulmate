package com.soulmate.security.authorizationServer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.token.TokenStore


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

    companion object {
        private const val REALM = "MY_OAUTH_REALM"
    }

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var tokenStore: TokenStore

    @Autowired
    private lateinit var userApprovalHandler: UserApprovalHandler

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private lateinit var authenticationManager: AuthenticationManager

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {

        clients
                .inMemory()
                .withClient("soulmate-client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret("secret")
                .accessTokenValiditySeconds(120) //Access token is only valid for 2 minutes.
                .refreshTokenValiditySeconds(600)//Refresh token is only valid for 10 minutes.
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
    }

    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
        oauthServer
                .realm("$REALM/client")
                .passwordEncoder(passwordEncoder)
    }
}