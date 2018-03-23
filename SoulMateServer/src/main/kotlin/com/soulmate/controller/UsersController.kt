package com.soulmate.controller

import com.soulmate.dtos.UserAccountDto
import com.soulmate.mapping.map
import com.soulmate.models.UserAccount
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


//@RestController(value = "/users")
@RestController
class UsersController {

    @Autowired
    lateinit var mapper: ModelMapper

    @Autowired
    lateinit var userService: UserService


    @GetMapping(value = "/users")
    fun getUserAccounts(): Iterable<UserAccountDto> {
        userService.addUser(UserAccount("dmitry", "vychikov"))
        val users = userService.getUsers()
        val map = users.map { userAccount -> mapper.map<UserAccountDto>(userAccount) }
        return map
    }

    @GetMapping(value = "/profile")
    fun getUserProfile(authentication: Authentication): com.soulmate.dtos.UserAccountDto? {
        val memberDetails = authentication.principal as MemberDetails
        val userAccount = userService.getUser(memberDetails.member.id)
        val map = mapper.map<UserAccountDto>(userAccount.get())
        return map
    }

    @PutMapping(value = "/profile")
    fun updateUserProfile(authentication: Authentication, @RequestBody userAccountDto: UserAccountDto) {
        val memberDetails = authentication.principal as MemberDetails
        val existingUserAccount = userService.getUser(memberDetails.member.id)
        val updatedUserAccount = mapper.map<UserAccount>(userAccountDto)
        existingUserAccount.ifPresent {
            it.firstName = updatedUserAccount.firstName
            it.lastName = updatedUserAccount.lastName
            userService.updateUser(it)
        }
    }
}
