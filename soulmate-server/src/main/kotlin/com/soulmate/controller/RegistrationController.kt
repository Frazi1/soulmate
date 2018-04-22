package com.soulmate.controller

import Endpoints.Companion.API_REGISTRATION
import com.soulmate.models.mapping.toMember
import com.soulmate.services.MemberService
import com.soulmate.services.UserService
import com.soulmate.validation.registarion.RegistrationMemberValidator
import dtos.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = [API_REGISTRATION])
class RegistrationController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    lateinit var registrationMemberValidator: RegistrationMemberValidator

    @InitBinder(/*"userRegistrationDto"*/)
    fun setBinder(binder: WebDataBinder) {
        binder.addValidators(registrationMemberValidator)
    }

    @PostMapping
    fun register(@Valid @RequestBody userRegistrationDto: UserRegistrationDto) {
        val member = userRegistrationDto.toMember()
        memberService.registerMember(member)
    }
}