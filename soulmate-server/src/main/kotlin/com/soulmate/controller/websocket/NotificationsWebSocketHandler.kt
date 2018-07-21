package com.soulmate.controller.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.soulmate.models.WebSocketSessionModel
import com.soulmate.models.dataAccess.Member
import com.soulmate.security.authorizationServer.MemberDetails
import com.soulmate.services.NatsService
import com.soulmate.services.helpers.JsonHelper
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@Component
class NotificationsWebSocketHandler(private val notificationClientSessionsHolder: NotificationClientSessionsHolder,
                                    private val natsService: NatsService,
                                    private val jsonHelper: JsonHelper,
                                    private val mapper: ObjectMapper
) : TextWebSocketHandler() {

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

        val userId = member.userAccount!!.id
        natsService.subscribeForUserChannel(userId) { message ->
            notificationClientSessionsHolder.activeSessions
                    .filter { it.member.userAccount?.id == userId }
                    .forEach {
                        it.session.sendMessage(TextMessage(message))
                    }
        }

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
        val userId = getMember(session).userAccount?.id
        if (notificationClientSessionsHolder.activeSessions.firstOrNull { it.member.userAccount?.id == userId } == null && userId != null) {
            natsService.unsunscribeFromUserChannel(userId)
        }
    }
}
