package com.soulmate.validation

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ValidationExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        val bindingResult: BindingResult = ex.bindingResult
        val fieldErrors: List<ValidationFieldError> = bindingResult.fieldErrors.map { fieldError ->
            ValidationFieldError(fieldError.field,
                    fieldError.code,
                    fieldError.rejectedValue)
        }
        val globalErrors: List<ValidationGlobalError> = bindingResult.globalErrors.map { objectError ->
            ValidationGlobalError(objectError.code)
        }

        return ResponseEntity(ValidationResponse(fieldErrors, globalErrors), HttpStatus.UNPROCESSABLE_ENTITY)
    }

}
