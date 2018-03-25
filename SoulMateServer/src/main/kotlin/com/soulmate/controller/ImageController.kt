package com.soulmate.controller

import com.soulmate.mapping.toProfileImage
import com.soulmate.repositories.ImageRepository
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
@RequestMapping(value = ["/image"])
class ImageController {

    @Autowired
    lateinit var imageService: ImageService

    @PostMapping
    fun uploadProfileImage(authentication: Authentication, @RequestBody profileImageDto: ProfileImageDto) {
        val profileImage = profileImageDto.toProfileImage()
        val memberDetails = authentication.principal as MemberDetails
        profileImage.userAccount = memberDetails.member.userAccount
        imageService.save(profileImage)
    }
}