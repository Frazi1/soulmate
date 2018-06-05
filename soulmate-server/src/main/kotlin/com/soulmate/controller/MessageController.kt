package com.soulmate.controller

import com.soulmate.controller.result.DeferredMessage
import com.soulmate.security.interfaces.IUserContextHolder
import com.soulmate.services.MessageService
import com.soulmate.services.UserService
import com.soulmate.shared.dtos.SendMessageDto
import com.soulmate.shared.dtos.UserDialogDto
import com.soulmate.shared.dtos.UserMessageDto
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/message")
class MessageController(userContextHolder: IUserContextHolder,
                        private val messageService: MessageService,
                        private val userService: UserService) : BaseController(userContextHolder) {

    private val queue = ArrayDeque<DeferredMessage>()
//    @GetMapping
//    fun getUserChats(): Iterable<UserAccountDto> {
//        return userService.getUserPairs(currentUserId)
//    }

    @GetMapping("{id}")
    fun getMessagesWithUser(@PathVariable("id") userId: Long,
                            @RequestParam("dateAfter") dateAfter: Optional<Long>): DeferredMessage {

        val deferredMessage = DeferredMessage(currentUserId, userId, Date(dateAfter.orElse(0)))
        queue.add(deferredMessage)
        return deferredMessage
//        return messageService.getNewDialogMessages(currentUserId, userId, dateAfter.orElse(Date(0)))
    }

    @PostMapping
    fun sendMessage(@RequestBody messageDto: SendMessageDto): UserMessageDto {
        return messageService.sendMessage(messageDto, currentUserId)
    }

    @GetMapping
    fun getUserDialogs(): Iterable<UserDialogDto> {
        return messageService.getUserDialogs(currentUserId)
    }

    @Scheduled(fixedDelay = 2000)
    private fun processQueue() {
        queue.forEach({
            if (it.isSetOrExpired) {
                queue.remove(it)
            } else {
                val res = messageService.getNewDialogMessages(it.firstUserAccount, it.secondUserAccount, it.dateAfter).sortedBy { it.sentAt }
                if (res.any()) {
                    it.setResult(res)
                    queue.remove(it)

                }
            }
        })
    }
}