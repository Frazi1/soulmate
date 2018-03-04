package com.soulmate.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @RequestMapping("/hello", method = [(RequestMethod.GET)])
    fun hello(): String {
        return "Hello world"
    }
}