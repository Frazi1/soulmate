package com.soulmate.security

import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SoulmateAuthorizationEntryPoint : BasicAuthenticationEntryPoint() {

    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authException: AuthenticationException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.setHeader("WWW-Authenticate", "Basic realm=$realmName");
        response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;

        val writer = response.writer;
        writer.print("Http Status: 401 ${authException.message}")
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        realmName = "Soulmate"
        super.afterPropertiesSet()
    }
}