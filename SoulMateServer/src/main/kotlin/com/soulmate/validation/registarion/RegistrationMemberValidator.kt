package com.soulmate.validation.registarion

import com.soulmate.mapping.map
import com.soulmate.models.Member
import com.soulmate.services.MemberService
import dtos.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class RegistrationMemberValidator : BaseValidator<UserRegistrationDto>() {

    @Autowired
    private lateinit var memberService: MemberService

    override fun supports(javaClass: Class<*>): Boolean = UserRegistrationDto::class.java.isAssignableFrom(javaClass)
    override fun validate(obj: Any?, error: Errors) {
//        val userRegistrationDto = obj as UserRegistrationDto
        val userRegistrationDto = getValidationEntity(obj)
        val member = mapper.map<Member>(userRegistrationDto)
        val memberExists = memberService.exists(member.email)
        if (memberExists) {
            error.reject(ErrorCodes.USER_ALREADY_EXISTS.description)
        }
    }

    @Bean
    override fun getValidator(): RegistrationMemberValidator {
        return RegistrationMemberValidator()
    }
}