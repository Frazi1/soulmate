package com.soulmate.models.mapping

import com.soulmate.models.Member
import com.soulmate.models.ProfileImage
import com.soulmate.models.UserAccount
import com.soulmate.models.UserMessage
import com.soulmate.shared.dtos.*
import org.apache.catalina.User

fun UserAccount.toUserAccountDto(): UserAccountDto {
    return UserAccountDto(
            id,
            firstName,
            firstName,
            profileImages.map { it.toProfileImageDto() },
            gender,
            personalStory,
            age)
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
    userAccount.age = age
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

fun UserMessage.toUserMessageDto(): UserMessageDto = UserMessageDto(id, sourceUserAccount!!.id, destinationUserAccount!!.id, content)

private fun createUserMessage(sourceUserAccount: UserAccount, destinationUserAccount: UserAccount, content: String): UserMessage {
    val userMessage = UserMessage()
    userMessage.sourceUserAccount = sourceUserAccount
    userMessage.destinationUserAccount = destinationUserAccount
    userMessage.content = content
    return userMessage
}

fun UserMessageDto.toUserMessage(sourceUserAccount: UserAccount, destinationUserAccount: UserAccount): UserMessage {
    return createUserMessage(sourceUserAccount, destinationUserAccount, content)
}

fun SendMessageDto.toUserMessage(sourceUserAccount: UserAccount, destinationUserAccount: UserAccount):UserMessage {
    return createUserMessage(sourceUserAccount, destinationUserAccount, content)
}