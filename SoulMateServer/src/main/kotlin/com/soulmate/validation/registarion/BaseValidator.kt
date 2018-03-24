package com.soulmate.validation.registarion

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.validation.Validator

@Component
abstract class BaseValidator<T>: Validator {

    protected fun getValidationEntity(obj:Any?): T {
        return obj as T
    }

    @Bean
    abstract fun getValidator(): Validator
}