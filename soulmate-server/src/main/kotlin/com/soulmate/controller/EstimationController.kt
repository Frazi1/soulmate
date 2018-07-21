package com.soulmate.controller

import com.soulmate.models.dataAccess.UserAccount
import com.soulmate.repositories.specs.TrueSpec
import com.soulmate.security.interfaces.IUserContextHolder
import com.soulmate.services.UserService
import com.soulmate.shared.Estimation
import com.soulmate.shared.dtos.UserAccountDto
import net.kaczmarzyk.spring.data.jpa.domain.Equal
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual
import net.kaczmarzyk.spring.data.jpa.domain.In
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/estimation")
class EstimationController(userContextHolder: IUserContextHolder,
                           val userService: UserService) : BaseController(userContextHolder) {


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
    @GetMapping()
    fun getUsersForEstimationWithFilter(@And(
            Spec(path = "firstName", spec = Equal::class),
            Spec(path = "gender", spec = In::class),
            Spec(path = "age", spec = GreaterThanOrEqual::class, params = ["ageFrom"]),
            Spec(path = "age", spec = LessThanOrEqual::class, params = ["ageTo"])
    ) spec: Specification<UserAccount>?): Iterable<UserAccountDto> {
        return userService.getUsersForEstimation(currentUserId, spec ?: TrueSpec())
    }
}