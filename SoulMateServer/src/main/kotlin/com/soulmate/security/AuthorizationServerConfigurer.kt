package com.soulmate.security


//@Configuration
//@EnableAuthorizationServer
//class AuthorizationServerConfigurer : AuthorizationServerConfigurerAdapter() {
//
//    @Autowired
//    lateinit var authenticationManager: AuthenticationManager
//
//    override fun configure(security: AuthorizationServerSecurityConfigurer) {
//        security.checkTokenAccess("isAuthenticated()")
//    }
//
//    override fun configure(clients: ClientDetailsServiceConfigurer) {
//        clients.inMemory().withClient("test")
//                .authorizedGrantTypes("client_credentials", "password")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
//                .resourceIds("oauth2-resource").accessTokenValiditySeconds(5000).secret("secret");
//    }
//
//    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
//        endpoints.authenticationManager(authenticationManager)
//    }
//}