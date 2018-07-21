package com.soulmate.services

import com.soulmate.controller.websocket.NotificationClientSessionsHolder
import com.soulmate.services.helpers.JsonHelper
import com.soulmate.shared.dtos.notifications.DialogNotificationDto
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class NotificationService(private val notificationClientSessionsHolder: NotificationClientSessionsHolder,
                          private val jsonHelper: JsonHelper) {
    private fun writeJson(session: WebSocketSession, json: String) = session.sendMessage(TextMessage(json))

    fun sendDialogMessageNotification(receiverId: Long, dialogNotification: DialogNotificationDto) {
        notificationClientSessionsHolder.activeSessions
                .filter { it.member.userAccount?.id == receiverId }
                .forEach {
                    writeJson(it.session, jsonHelper.toJson(dialogNotification)) }
    }
}