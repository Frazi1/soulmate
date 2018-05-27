package com.soulmate.controller

import com.soulmate.security.interfaces.IUserContextHolder
import org.springframework.web.bind.annotation.RestController

@RestController
class BaseController(private val userContextHolder: IUserContextHolder) {

    val currentUserId: Long
        get() = userContextHolder.currentUserId
}