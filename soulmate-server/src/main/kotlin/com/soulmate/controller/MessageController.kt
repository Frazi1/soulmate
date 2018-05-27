package com.soulmate.controller

import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.helpers.MessageService
import com.soulmate.shared.dtos.SendMessageDto
import com.soulmate.shared.dtos.UserMessageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/message")
class MessageController {

    @Autowired
    private lateinit var messageService: MessageService

    @PostMapping
    fun sendMessage(@RequestBody messageDto: SendMessageDto): ResponseEntity<Long> {
        val currentUser = SecurityContextHolder.getContext().authentication.principal as MemberDetails
        val sentMessage = messageService.sendMessage(messageDto, currentUser.member.id)
        return ResponseEntity.ok(sentMessage.id)
    }
}