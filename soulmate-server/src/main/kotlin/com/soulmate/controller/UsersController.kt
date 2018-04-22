package com.soulmate.controller

import Endpoints.Companion.API_USERS
import Endpoints.Companion.USER_PROFILE_PATH
import dtos.UserAccountDto
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(API_USERS)
class UsersController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getUserAccounts(): Iterable<UserAccountDto> {
        return userService.getUsers()
    }

    @GetMapping(value = [USER_PROFILE_PATH])
    fun getUserProfile(authentication: Authentication): UserAccountDto {
        val memberDetails = authentication.principal as MemberDetails
        return userService.getUser(memberDetails.member.id)
    }

    @PutMapping(value = [USER_PROFILE_PATH])
    fun updateUserProfile(authentication: Authentication, @RequestBody userAccountDto: UserAccountDto) {
        //TODO DV: SECURITY check if userAccountDto ID is the same as the authenticated user id
        userService.updateUser(userAccountDto)
    }

}
