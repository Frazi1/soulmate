package com.soulmate.models.mapping

import com.soulmate.Constants.Companion.NO_IMAGE_ID
import com.soulmate.models.dataAccess.Member
import com.soulmate.models.dataAccess.ProfileImage
import com.soulmate.models.dataAccess.UserAccount
import com.soulmate.models.dataAccess.UserMessage
import com.soulmate.shared.dtos.*
import com.soulmate.shared.dtos.notifications.DialogNotificationDto
import java.time.ZoneOffset
import java.util.*

fun UserAccount.toUserAccountDto(): UserAccountDto {
    return UserAccountDto(
            id,
            firstName,
            firstName,
            if (profileImages.any()) profileImages.map { it.toProfileImageDto() } else listOf(ProfileImageDto(NO_IMAGE_ID)),
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

fun UserMessage.toUserMessageDto(): UserMessageDto = UserMessageDto(id, sourceUserAccount!!.id, destinationUserAccount!!.id, content, Date.from(sentTime.toInstant(ZoneOffset.UTC)))

fun UserMessage.toDialogNotificationDto(dialogName: String): DialogNotificationDto = DialogNotificationDto(sourceUserAccount!!.id, dialogName, toUserMessageDto() )

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

fun SendMessageDto.toUserMessage(sourceUserAccount: UserAccount, destinationUserAccount: UserAccount): UserMessage {
    return createUserMessage(sourceUserAccount, destinationUserAccount, content)
}