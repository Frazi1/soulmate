package com.soulmate.shared.dtos

import com.soulmate.shared.dtos.interfaces.IMessage

class UserMessageDto(var id: Long = 0,
                     var fromUserId: Long = 0,
                     var toUserId: Long = 0,
                     override var content: String = "") : IMessage