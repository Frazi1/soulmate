package com.soulmate.controller

import com.soulmate.mapping.map
import com.soulmate.models.Member
import com.soulmate.services.MemberService
import com.soulmate.services.UserService
import com.soulmate.validation.registarion.RegistrationMemberValidator
import dtos.UserRegistrationDto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/registration"])
class RegistrationController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Autowired
    lateinit var registrationMemberValidator: RegistrationMemberValidator

    @InitBinder(/*"userRegistrationDto"*/)
    fun setBinder(binder: WebDataBinder) {
        binder.addValidators(registrationMemberValidator)
    }


    @RequestMapping(value = ["/login"], method = [(RequestMethod.GET), (RequestMethod.POST)])
    fun login(): String {
        return "ok";
    }

    @PostMapping
    fun register(@Valid @RequestBody userRegistrationDto: UserRegistrationDto) {
        val member = modelMapper.map<Member>(userRegistrationDto)
        memberService.add(member)
    }

    @GetMapping
    fun test():UserRegistrationDto {
        return UserRegistrationDto("valera", "test")
    }

}