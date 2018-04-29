package com.soulmate.services

import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import com.soulmate.models.mapping.toProfileImage
import com.soulmate.repositories.ImageRepository
import com.soulmate.utils.extensions.resize
import com.soulmate.validation.exceptions.BusinessException
import dtos.UploadImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ImageService {

    companion object {
        private const val IMAGE_THUMBNAIL_SIZE = 400
        private const val IMAGE_MAX_SIZE = 1920
    }

    @Autowired
    private lateinit var imageRepository: ImageRepository

    fun updateMainProfileImage(userId: Long, newImageId: Long) {
        val userImages: List<ProfileImage> = imageRepository.findAllByUserAccountId(userId)
        val newImage: ProfileImage? = userImages.find { it.id == newImageId }
        newImage?.isMainImage = true
        userImages.stream()
                .filter({ it.id != newImageId })
                .forEach {
                    it.isMainImage = false
                }
        imageRepository.saveAll(userImages)
    }

    @Transactional
    fun save(profileImage: ProfileImage) {
        imageRepository.save(profileImage)
        if (profileImage.isMainImage) {
            profileImage.userAccount?.let {
                updateMainProfileImage(it.id, profileImage.id)
            }
        }
    }

    fun uploadProfileImage(currentUserAccount: UserAccount?, uploadImageDto: UploadImageDto) {
        val profileImage = uploadImageDto.toProfileImage()
        profileImage.userAccount = currentUserAccount
        profileImage.resize(IMAGE_MAX_SIZE)
        save(profileImage)
    }

//    fun getMainProfileImage(userId: Long): UploadImageDto? {
//        val mainImage: Optional<ProfileImage> = imageRepository.findByUserAccountIdAndIsMainImageTrue(userId)
//        return if (mainImage.isPresent) {
//            val image: ProfileImage = mainImage.get()
//            return image.toUploadImage().getThumbnail(IMAGE_THUMBNAIL_SIZE)
//        } else
//            null
//    }

    fun getImageBytes(id: Long, compressedWidth: Int?): ByteArray? {
        val profileImage = imageRepository.findById(id)
        return profileImage
                .orElseThrow {BusinessException("Image with id $id does not exist")}
                .data
    }
}