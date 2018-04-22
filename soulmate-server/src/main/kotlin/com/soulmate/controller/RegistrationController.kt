package com.soulmate.controller

import com.soulmate.services.MemberService
import com.soulmate.validation.registarion.RegistrationMemberValidator
import dtos.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["api/registration"])
class RegistrationController {

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    lateinit var registrationMemberValidator: RegistrationMemberValidator

    @InitBinder()
    fun setBinder(binder: WebDataBinder) {
        binder.addValidators(registrationMemberValidator)
    }

    @PostMapping
    fun register(@Valid @RequestBody userRegistrationDto: UserRegistrationDto): ResponseEntity<HttpStatus> {
        memberService.registerMember(userRegistrationDto)
        return ResponseEntity.ok().body(HttpStatus.CREATED)
    }
}