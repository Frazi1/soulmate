package com.soulmate.controller

import com.soulmate.security.interfaces.IUserContextHolder
import com.soulmate.services.ImageService
import com.soulmate.shared.dtos.UploadImageDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["api/image"])
class ImageController(userContextHolder: IUserContextHolder,
                      private val imageService: ImageService) : BaseController(userContextHolder) {

    @GetMapping("{id}", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun getImage(@PathVariable("id") id: Long, compressedWidth: Int? = null): ByteArray? {
        return imageService.getImageBytes(id, compressedWidth)
    }

    @PostMapping
    fun uploadProfileImage(@RequestBody uploadImageDto: UploadImageDto) {
        imageService.uploadProfileImage(currentUserId, uploadImageDto)
    }
}