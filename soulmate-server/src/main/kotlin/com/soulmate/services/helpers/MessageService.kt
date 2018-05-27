package com.soulmate.services.helpers

import com.soulmate.models.mapping.toUserMessage
import com.soulmate.models.mapping.toUserMessageDto
import com.soulmate.repositories.UserMessageRepository
import com.soulmate.repositories.UserRepository
import com.soulmate.shared.dtos.SendMessageDto
import com.soulmate.shared.dtos.UserMessageDto
import com.soulmate.validation.exceptions.UserDoesNotExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService {
    @Autowired
    private lateinit var userMessageRepository: UserMessageRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    fun sendMessage(message: SendMessageDto, sourceUserId: Long): UserMessageDto {
        val sourceUserAccount = userRepository
                .findById(sourceUserId).orElseThrow { UserDoesNotExistException(sourceUserId) }
        val destinationUserAccount = userRepository
                .findById(message.userId).orElseThrow { UserDoesNotExistException(message.userId) }

        val userMessage = message.toUserMessage(sourceUserAccount, destinationUserAccount)
        userMessageRepository.save(userMessage)
        return userMessage.toUserMessageDto()
    }
}