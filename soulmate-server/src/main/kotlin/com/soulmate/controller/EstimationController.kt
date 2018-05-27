package com.soulmate.controller

import com.soulmate.security.interfaces.IUserContextHolder
import com.soulmate.services.UserService
import com.soulmate.shared.Estimation
import com.soulmate.shared.dtos.UserAccountDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/estimation")
class EstimationController(userContextHolder: IUserContextHolder,
                           val userService: UserService) : BaseController(userContextHolder) {

    @GetMapping
    fun getAccountsForEstimation(authentication: Authentication): Iterable<UserAccountDto> {
        return userService.getUsersForEstimation(currentUserId)
    }

    @PostMapping("{id}")
    fun addUserEstimation(
            @PathVariable("id") id: Long,
            @RequestParam("estimation") estimation: Estimation
    ): ResponseEntity<Long> {
        val createdEstimationId: Long = userService.addUserEstimation(currentUserId, id, estimation)
        return ResponseEntity.ok().body(createdEstimationId)
    }

    @DeleteMapping("{id}")
    fun removeUserEstimation(@PathVariable("id") id: Long): ResponseEntity<HttpStatus> {
        userService.removeUserEstimations(currentUserId, listOf(id))
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/all")
    fun undoAllLikeEstimations(): ResponseEntity<HttpStatus> {
        userService.removeAllUserEstimations(currentUserId)
        return ResponseEntity(HttpStatus.OK)
    }
}