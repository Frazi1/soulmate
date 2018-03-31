package com.soulmate.security.configuration

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

import javax.servlet.Filter

class HelloWorldInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getRootConfigClasses(): Array<Class<*>>? {
        return arrayOf(HelloWorldConfiguration::class.java)
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return null
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    override fun getServletFilters(): Array<Filter>? {
        return arrayOf(CORSFilter())
    }

}