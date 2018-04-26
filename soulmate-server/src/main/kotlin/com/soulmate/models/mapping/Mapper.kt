package com.soulmate.models.mapping

import com.soulmate.models.Member
import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import dtos.ProfileEstimationDto
import dtos.ProfileImageDto
import dtos.UserAccountDto
import dtos.UserRegistrationDto

fun UserAccount.toUserAccountDto(
        profileImages: Iterable<ProfileImageDto> = this.profileImages
                .map { it.toProfileImageDto() }): UserAccountDto {
    return UserAccountDto(
            id,
            firstName,
            firstName,
            profileImages.toMutableList(),
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

fun ProfileImageDto.toProfileImage(): ProfileImage {
    return ProfileImage(order, data, description, isMainImage)
}

fun ProfileImage.toProfileImageDto(data: ByteArray = this.data!!): ProfileImageDto {
    return ProfileImageDto(order, data, description, isMainImage)
}
