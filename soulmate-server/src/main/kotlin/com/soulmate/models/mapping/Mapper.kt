package com.soulmate.models.mapping

import com.soulmate.models.Member
import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import dtos.*

fun UserAccount.toUserAccountDto(): UserAccountDto {
    return UserAccountDto(
            id,
            firstName,
            firstName,
            profileImages.map { it.toProfileImageDto() },
            gender,
            personalStory)
}

fun UserAccount.toAccountEstimationDto(userAccountToCheck: UserAccount): ProfileEstimationDto {
    return ProfileEstimationDto(
            id,
            firstName,
            lastName,
            profileImages.map { it.toProfileImageDto() },
            gender,
            personalStory,
            likedCollection.any { it.id == userAccountToCheck.id }
    )
}

fun UserAccountDto.toExistingUserAccount(): UserAccount {
    return this.toExistingUserAccount(UserAccount())
}

fun UserAccountDto.toExistingUserAccount(userAccount: UserAccount): UserAccount {
    userAccount.id = id
    userAccount.firstName = firstName
    userAccount.lastName = lastName
    userAccount.gender = gender
    userAccount.personalStory = personalStory
    return userAccount
}

fun Member.toUserRegistrationDto(): UserRegistrationDto {
    return UserRegistrationDto(email, passwordHash,
            if (userAccount != null) userAccount!!.firstName!! else ""
    )
}

fun UserRegistrationDto.toMember(): Member {
    return Member(email, passwordHash, null)
}

fun UploadImageDto.toProfileImage(): ProfileImage {
    return ProfileImage(order, data, description, isMainImage)
}

fun ProfileImage.toUploadImage(data: ByteArray = this.data!!): UploadImageDto {
    return UploadImageDto(order, data, description, isMainImage)
}

fun ProfileImage.toProfileImageDto(): ProfileImageDto {
    return ProfileImageDto(id, description, isMainImage)
}
