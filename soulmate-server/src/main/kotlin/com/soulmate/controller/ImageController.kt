package com.soulmate.controller

import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.ImageService
import com.soulmate.shared.dtos.UploadImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["api/image"])
class ImageController {

    @Autowired
    lateinit var imageService: ImageService

    @GetMapping("{id}", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun getImage(@PathVariable("id") id: Long, compressedWidth: Int? = null) : ByteArray? {
        return imageService.getImageBytes(id, compressedWidth)
    }

    @PostMapping
    fun uploadProfileImage(authentication: Authentication, @RequestBody uploadImageDto: UploadImageDto) {
        val memberDetails = authentication.principal as MemberDetails
        imageService.uploadProfileImage(memberDetails.member.userAccount, uploadImageDto)
    }
}