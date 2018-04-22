package com.soulmate.controller

import Endpoints.Companion.API_IMAGE
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.ImageService
import dtos.ProfileImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [API_IMAGE])
class ImageController {

    @Autowired
    lateinit var imageService: ImageService

    @PostMapping
    fun uploadProfileImage(authentication: Authentication, @RequestBody profileImageDto: ProfileImageDto) {
        val memberDetails = authentication.principal as MemberDetails
        imageService.uploadProfileImage(memberDetails.member.userAccount, profileImageDto)
    }
}