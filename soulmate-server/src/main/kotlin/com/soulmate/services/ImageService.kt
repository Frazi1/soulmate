package com.soulmate.services

import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import com.soulmate.models.mapping.toProfileImage
import com.soulmate.repositories.ImageRepository
import dtos.ProfileImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.*
import javax.transaction.Transactional

@Service
class ImageService {

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

<<<<<<< HEAD

=======
    fun uploadProfileImage(currentUserAccount: UserAccount?, profileImageDto: ProfileImageDto) {
        val profileImage = profileImageDto.toProfileImage()
        profileImage.userAccount = currentUserAccount
        save(profileImage)
    }
>>>>>>> master
}