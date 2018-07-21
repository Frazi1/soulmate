package com.soulmate.models

import com.soulmate.models.dataAccess.Member
import org.springframework.web.socket.WebSocketSession

class WebSocketSessionModel(val session: WebSocketSession,
                            val member: Member) {
    val id: String
        get() = session.id
}
