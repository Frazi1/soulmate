package com.soulmate.shared.dtos.notifications

import com.soulmate.shared.dtos.UserMessageDto

class DialogNotificationDto(var dialogId: Long = 0,
                            var dialogName: String = "",
                            var messageDto: UserMessageDto = UserMessageDto())