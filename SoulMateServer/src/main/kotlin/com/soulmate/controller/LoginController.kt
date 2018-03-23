package com.soulmate.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @RequestMapping(value = "/login", method = [RequestMethod.GET, RequestMethod.POST])
    fun login(): String {
        return "ok";
    }
}