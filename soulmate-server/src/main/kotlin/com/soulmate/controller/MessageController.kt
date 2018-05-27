package com.soulmate.controller

import com.soulmate.security.interfaces.IUserContextHolder
import com.soulmate.services.UserService
import com.soulmate.services.MessageService
import com.soulmate.shared.dtos.SendMessageDto
import com.soulmate.shared.dtos.UserAccountDto
import com.soulmate.shared.dtos.UserMessageDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/message")
class MessageController(userContextHolder: IUserContextHolder,
                        private val messageService: MessageService,
                        private val userService: UserService) : BaseController(userContextHolder) {

    @GetMapping
    fun getUserChats(): Iterable<UserAccountDto> {
        return userService.getUserPairs(currentUserId)
    }

    @GetMapping("{id}")
    fun getMessagesWithUser(@PathVariable("id") userId: Long): Iterable<UserMessageDto> {
        return messageService.getMessagesForUsers(currentUserId, userId)
    }

    @PostMapping
    fun sendMessage(@RequestBody messageDto: SendMessageDto): UserMessageDto {
        return messageService.sendMessage(messageDto, currentUserId)
    }
}