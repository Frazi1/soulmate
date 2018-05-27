package com.soulmate.controller

import com.soulmate.security.interfaces.IUserContextHolder
import com.soulmate.services.UserService
import com.soulmate.shared.dtos.UserAccountDto
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/users")
class UsersController(userContextHolder: IUserContextHolder,
                      private val userService: UserService) : BaseController(userContextHolder) {

    @GetMapping
    fun getUserAccounts(): Iterable<UserAccountDto> {
        return userService.getUsers()
    }

    @GetMapping(value = ["/profile"])
    fun getUserProfile(): UserAccountDto {
        return userService.getUser(currentUserId)
    }

    @PutMapping(value = ["/profile"])
    fun updateUserProfile(@RequestBody userAccountDto: UserAccountDto) {
        //TODO DV: SECURITY check if userAccountDto ID is the same as the authenticated user id
        userService.updateUser(userAccountDto)
    }

}
