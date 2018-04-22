package com.soulmate.controller

import Endpoints.Companion.API_ESTIMATION
import com.soulmate.models.UserAccount
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import dtos.AccountEstimationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(API_ESTIMATION)
class EstimationController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun getAccountsForEstimation(authentication: Authentication): Iterable<AccountEstimationDto> {
        val currentUser = authentication.principal as MemberDetails
        return userService.getUsersForEstimation(currentUser.member.id)
    }

    @PostMapping("{id}")
    fun addLikeEstimationForUserAccount(@PathVariable("id") id: Long, authentication: Authentication): ResponseEntity<HttpStatus> {
        val currentUser = authentication.principal as MemberDetails
        userService.addLikeFromUserAccountTo(currentUser.member.id, id)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("{id}")
    fun undoLikeEstimationForUserAccount(@PathVariable("id") id: Long, authentication: Authentication): ResponseEntity<HttpStatus> {
        val currentUser = authentication.principal as MemberDetails
        userService.undoLikeEstimationForUserAccount(currentUser.member.id, id)
        return ResponseEntity(HttpStatus.OK)
    }
}