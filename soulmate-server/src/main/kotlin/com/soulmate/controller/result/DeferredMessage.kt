package com.soulmate.controller.result

import com.soulmate.shared.dtos.UserMessageDto
import org.springframework.web.context.request.async.DeferredResult
import java.util.*

class DeferredMessage(val firstUserAccount: Long,
                      val secondUserAccount: Long,
                      val dateAfter: Date) : DeferredResult<Iterable<UserMessageDto>>(30000, listOf<Iterable<UserMessageDto>>()) {
}