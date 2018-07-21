package com.soulmate.services

import com.soulmate.models.mapping.toDialogNotificationDto
import com.soulmate.models.mapping.toUserMessage
import com.soulmate.models.mapping.toUserMessageDto
import com.soulmate.repositories.MessageRepository
import com.soulmate.repositories.UserRepository
import com.soulmate.repositories.specs.UserMessagesSpec
import com.soulmate.shared.dtos.SendMessageDto
import com.soulmate.shared.dtos.UserDialogDto
import com.soulmate.shared.dtos.UserMessageDto
import com.soulmate.validation.exceptions.UserDoesNotExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService {
    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var notificationService: NotificationService

    fun sendMessage(message: SendMessageDto, sourceUserId: Long): UserMessageDto {
        val sourceUserAccount = userRepository
                .findById(sourceUserId).orElseThrow { UserDoesNotExistException(sourceUserId) }
        val destinationUserAccount = userRepository
                .findById(message.userId).orElseThrow { UserDoesNotExistException(message.userId) }

        val userMessage = message.toUserMessage(sourceUserAccount, destinationUserAccount)
        messageRepository.save(userMessage)
        val dialogNotificationDto = userMessage.toDialogNotificationDto(sourceUserAccount.fullName)
        notificationService.sendDialogMessageNotification(dialogNotificationDto.messageDto.toUserId, dialogNotificationDto)
        return dialogNotificationDto.messageDto
    }

    fun getMessagesForUsers(firstUserId: Long, secondUserId: Long): Iterable<UserMessageDto> {
        val messages = messageRepository.findAll(UserMessagesSpec(firstUserId, secondUserId))
        return messages.map { it.toUserMessageDto() }
    }

    fun getUserDialogs(currentUserId: Long): Iterable<UserDialogDto> {
        val userPairs = userService.getUserPairs(currentUserId)
        return userPairs.map {
            UserDialogDto(it, getMessagesForUsers(currentUserId, it.id).sortedByDescending { it.sentAt }.firstOrNull())
        }
    }

    fun getNewDialogMessages(firstUserId: Long, secondUserId: Long, dateAfter: Date): Iterable<UserMessageDto> {
        val messages = getMessagesForUsers(firstUserId, secondUserId)
                .filter { it.sentAt.after(dateAfter) }
        return messages
    }
}