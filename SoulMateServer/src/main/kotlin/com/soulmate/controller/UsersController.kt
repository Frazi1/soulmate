package com.soulmate.controller

import com.soulmate.dtos.UserAccountDto
import com.soulmate.mapping.map
import com.soulmate.models.UserAccount
import com.soulmate.services.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


//@RestController(value = "/users")
@RestController
class UsersController {

    @Autowired
    lateinit var mapper: ModelMapper

    @Autowired
    lateinit var userService: UserService


    @GetMapping(value = ["/users"])
    fun getUserAccounts(): Iterable<UserAccountDto> {
        userService.addUser(UserAccount("dmitry", "vychikov"))
        val users = userService.getUsers()
        val map = users.map { userAccount -> mapper.map<UserAccountDto>(userAccount) }
        return map
    }
}
