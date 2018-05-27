package com.soulmate.shared.dtos

import com.soulmate.shared.dtos.interfaces.IMessage

class SendMessageDto(var userId: Long = 0,
                     override var content: String = "") : IMessage