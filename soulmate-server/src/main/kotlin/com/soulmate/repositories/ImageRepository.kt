package com.soulmate.repositories

import com.soulmate.models.ProfileImage
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ImageRepository : CrudRepository<ProfileImage, Long> {
    fun findAllByUserAccountId(userId: Long): List<ProfileImage>

    fun findByUserAccountIdAndIsMainImageTrue(userAccountId: Long) : ProfileImage
}