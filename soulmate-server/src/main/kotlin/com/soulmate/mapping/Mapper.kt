package com.soulmate.mapping

import dtos.UserAccountDto
import com.soulmate.models.Member
import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import dtos.ProfileImageDto
import dtos.UserRegistrationDto

fun UserAccount.toUserAccountDto(): UserAccountDto {
    return UserAccountDto(
            this.id,
            this.firstName,
            this.firstName,
            this.profileImages.map { it.toProfileImageDto() },
            this.gender,
            this.personalStory)
}

fun UserAccountDto.toUserAccount(): UserAccount {
    return this.toUserAccount(UserAccount())
}

fun UserAccountDto.toUserAccount(userAccount: UserAccount): UserAccount {
    userAccount.id = this.id
    userAccount.firstName = this.firstName
    userAccount.lastName = this.lastName
    userAccount.gender = this.gender
    userAccount.personalStory = this.personalStory
    return userAccount
}

fun Member.toUserRegistrationDto(): UserRegistrationDto {
    return UserRegistrationDto(this.email,
            this.passwordHash,
            if (this.userAccount != null) this.userAccount!!.firstName!! else "")
}

fun UserRegistrationDto.toMember(): Member {
    return Member(this.email, this.passwordHash, null)
}

fun ProfileImageDto.toProfileImage(): ProfileImage {
    return ProfileImage(this.order, this.data, this.description, this.isMainImage)
}

fun ProfileImage.toProfileImageDto(): ProfileImageDto {
    return ProfileImageDto(this.order, this.data, this.description, this.isMainImage)
}
