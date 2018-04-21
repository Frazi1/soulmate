package com.soulmate.controller

import Endpoints.Companion.API_USERS
import Endpoints.Companion.USER_PROFILE_PATH
import dtos.UserAccountDto
import com.soulmate.mapping.toUserAccount
import com.soulmate.mapping.toUserAccountDto
import com.soulmate.models.UserAccount
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping(API_USERS)
class UsersController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getUserAccounts(): Iterable<UserAccountDto> {
        val users = userService.getUsers()
        return users.map { userAccount -> userAccount.toUserAccountDto()}
    }

    @GetMapping(value = [USER_PROFILE_PATH])
    fun getUserProfile(authentication: Authentication): UserAccountDto? {
        val memberDetails = authentication.principal as MemberDetails
        val userAccount = userService.getUser(memberDetails.member.id)

        val map = userAccount.get().toUserAccountDto()
        return map
    }

    @PutMapping(value = [USER_PROFILE_PATH])
    fun updateUserProfile(authentication: Authentication, @RequestBody userAccountDto: UserAccountDto) {
        val memberDetails: MemberDetails = authentication.principal as MemberDetails
        val existingUserAccount: Optional<UserAccount> = userService.getUser(memberDetails.member.id)
        val updatedUserAccount: UserAccount = userAccountDto.toUserAccount()
        existingUserAccount.ifPresent {
//            it.firstName = updatedUserAccount.firstName
//            it.lastName = updatedUserAccount.lastName
//            it.personalStory = updatedUserAccount.personalStory
            userAccountDto.toUserAccount(it)
            userService.updateUser(it)
        }
    }

}
