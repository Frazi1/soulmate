package com.soulmate.controller

import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import dtos.UserAccountDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/users")
class UsersController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getUserAccounts(): Iterable<UserAccountDto> {
        return userService.getUsers()
    }

    @GetMapping(value = ["/profile"])
    fun getUserProfile(authentication: Authentication): UserAccountDto {
        val memberDetails = authentication.principal as MemberDetails
        return userService.getUser(memberDetails.member.id)
    }

    @PutMapping(value = ["/profile"])
    fun updateUserProfile(authentication: Authentication, @RequestBody userAccountDto: UserAccountDto) {
        //TODO DV: SECURITY check if userAccountDto ID is the same as the authenticated user id
        userService.updateUser(userAccountDto)
    }

}
