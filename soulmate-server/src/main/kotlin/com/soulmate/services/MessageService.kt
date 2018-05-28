package com.soulmate.services

import com.soulmate.models.UserAccount
import com.soulmate.models.UserMessage
import com.soulmate.models.mapping.toUserMessage
import com.soulmate.models.mapping.toUserMessageDto
import com.soulmate.repositories.MessageRepository
import com.soulmate.repositories.UserRepository
import com.soulmate.shared.dtos.SendMessageDto
import com.soulmate.shared.dtos.UserMessageDto
import com.soulmate.validation.exceptions.UserDoesNotExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService {
    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    fun sendMessage(message: SendMessageDto, sourceUserId: Long): UserMessageDto {
        val sourceUserAccount = userRepository
                .findById(sourceUserId).orElseThrow { UserDoesNotExistException(sourceUserId) }
        val destinationUserAccount = userRepository
                .findById(message.userId).orElseThrow { UserDoesNotExistException(message.userId) }

        val userMessage = message.toUserMessage(sourceUserAccount, destinationUserAccount)
        messageRepository.save(userMessage)
        return userMessage.toUserMessageDto()
    }

    fun getMessagesForUsers(firstUserId: Long, secondUserId: Long): Iterable<UserMessageDto> {
        val messages = messageRepository.findAll { r, cq, cb ->
            val p1 = cb.and(
                    cb.equal(r.get<UserAccount>(UserMessage::sourceUserAccount.name).get<Long>(UserAccount::id.name), firstUserId),
                    cb.equal(r.get<UserAccount>(UserMessage::destinationUserAccount.name).get<Long>(UserAccount::id.name), secondUserId)
            )
            val p2 = cb.and(
                    cb.equal(r.get<UserAccount>(UserMessage::sourceUserAccount.name).get<Long>(UserAccount::id.name), secondUserId),
                    cb.equal(r.get<UserAccount>(UserMessage::destinationUserAccount.name).get<Long>(UserAccount::id.name), firstUserId)
            )
            return@findAll cb.or(p1, p2)
        }
        return messages.map { it.toUserMessageDto() }
    }
}