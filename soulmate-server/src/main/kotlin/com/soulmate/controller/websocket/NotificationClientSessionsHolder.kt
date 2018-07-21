package com.soulmate.controller.websocket

import com.soulmate.models.WebSocketSessionModel
import org.springframework.stereotype.Component
import java.util.concurrent.CopyOnWriteArrayList

@Component
class NotificationClientSessionsHolder {
    val activeSessions = CopyOnWriteArrayList<WebSocketSessionModel>()
}