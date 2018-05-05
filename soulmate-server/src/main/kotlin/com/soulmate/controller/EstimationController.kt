package com.soulmate.controller

import com.soulmate.models.UserAccount
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.UserService
import com.soulmate.shared.Estimation
import com.soulmate.shared.dtos.UserAccountDto
import net.kaczmarzyk.spring.data.jpa.domain.Equal
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
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
    fun getAccountsForEstimation(authentication: Authentication): Iterable<UserAccountDto> {
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
        val createdEstimationId: Long = userService.addUserEstimation(currentUser.member.id, id, estimation)
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

    @GetMapping(value = ["/users"] )
    fun getUsersForEstimationWithFilter(@And(*[
        Spec(path = "firstName", spec = Equal::class),
        Spec(path = "gender", spec = Equal::class)]) spec: Specification<UserAccount>): Iterable<UserAccountDto> {
        return userService.findBySpec(spec)
    }
}