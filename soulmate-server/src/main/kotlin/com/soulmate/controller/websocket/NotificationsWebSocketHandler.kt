package com.soulmate.controller.websocket

import com.soulmate.models.WebSocketSessionModel
import com.soulmate.models.dataAccess.Member
import com.soulmate.security.authorizationServer.MemberDetails
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@Component
class NotificationsWebSocketHandler(var notificationClientSessionsHolder: NotificationClientSessionsHolder) : TextWebSocketHandler() {

    private fun getMember(session: WebSocketSession): Member {
        val principal = session.principal
        val authentication = principal as OAuth2Authentication
        val memberDetails = authentication.principal as MemberDetails
        return memberDetails.member
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        System.out.println("$session.id socket opened")
        val member = getMember(session)
        notificationClientSessionsHolder.activeSessions.addIfAbsent(WebSocketSessionModel(session, member))
//        session.sendMessage(TextMessage("hello ${member.email}"))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        System.out.println(message.payload)
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        System.out.println(exception.message)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        notificationClientSessionsHolder.activeSessions.removeIf { it.id == session.id }
    }
}
