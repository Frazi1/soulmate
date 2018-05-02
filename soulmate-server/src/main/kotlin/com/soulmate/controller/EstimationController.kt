package com.soulmate.controller

import com.soulmate.models.Estimation
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import dtos.ProfileEstimationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/estimation")
class EstimationController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun getAccountsForEstimation(authentication: Authentication): Iterable<ProfileEstimationDto> {
        val currentUser = authentication.principal as MemberDetails
        return userService.getUsersForEstimation(currentUser.member.id)
    }

    @PostMapping("{id}")
    fun addUserEstimation(
            @PathVariable("id") id: Long,
            @RequestParam("estimation") estimation: Estimation,
            authentication: Authentication
    ): ResponseEntity<Long> {
        val currentUser = authentication.principal as MemberDetails
        val createdEstimationId: Long = userService.addUserEstimation(currentUser.member.id, id, Estimation.LIKE)
        return ResponseEntity.ok().body(createdEstimationId)
    }

    @DeleteMapping("{id}")
    fun removeUserEstimation(@PathVariable("id") id: Long, authentication: Authentication): ResponseEntity<HttpStatus> {
        val currentUser = authentication.principal as MemberDetails
        userService.removeUserEstimations(currentUser.member.id, listOf(id))
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/all")
    fun undoAllLikeEstimations(authentication: Authentication): ResponseEntity<HttpStatus> {
        val currentMemberDetails = authentication.principal as MemberDetails
        userService.removeAllUserEstimations(currentMemberDetails.member.id)
        return ResponseEntity(HttpStatus.OK)
    }
}