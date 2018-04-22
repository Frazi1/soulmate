package com.soulmate.models.mapping

import dtos.UserAccountDto
import com.soulmate.models.Member
import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import dtos.AccountEstimationDto
import dtos.ProfileImageDto
import dtos.UserRegistrationDto

fun UserAccount.toUserAccountDto(): UserAccountDto {
    return UserAccountDto(
            id,
            firstName,
            firstName,
            profileImages.map { it.toProfileImageDto() },
            gender,
            personalStory)
}

fun UserAccount.toAccountEstimationDto(userAccountToCheck: UserAccount): AccountEstimationDto {
    return AccountEstimationDto(
            id,
            firstName,
            lastName,
            profileImages.map { it.toProfileImageDto() },
            gender,
            personalStory,
            likedCollection.any { it.id == userAccountToCheck.id }
    )
}

fun UserAccountDto.toUserAccount(): UserAccount {
    return this.toUserAccount(UserAccount())
}

fun UserAccountDto.toUserAccount(userAccount: UserAccount): UserAccount {
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

fun ProfileImage.toProfileImageDto(): ProfileImageDto {
    return ProfileImageDto(order, data, description, isMainImage)
}
