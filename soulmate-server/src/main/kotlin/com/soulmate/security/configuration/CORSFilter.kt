package com.soulmate.security.configuration

import javax.servlet.*
import javax.servlet.http.HttpServletResponse
import java.io.IOException


//class CORSFilter : Filter {
//
//    @Throws(IOException::class, ServletException::class)
//    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
//        println("Filtering on...........................................................")
//        val response = res as HttpServletResponse
//        response.setHeader("Access-Control-Allow-Origin", "*")
//        response.setHeader("Access-Control-Allow-Credentials", "true")
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE")
//        response.setHeader("Access-Control-Max-Age", "3600")
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers")
//
//        chain.doFilter(req, res)
//    }
//
//    override fun init(filterConfig: FilterConfig) {}
//
//    override fun destroy() {}
//
//}