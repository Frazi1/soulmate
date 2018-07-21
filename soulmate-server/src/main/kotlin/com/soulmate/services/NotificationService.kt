package com.soulmate.services

import com.soulmate.services.helpers.JsonHelper
import com.soulmate.shared.dtos.notifications.DialogNotificationDto
import org.springframework.stereotype.Service

@Service
class NotificationService(private val jsonHelper: JsonHelper,
                          private val natsService: NatsService) {

    fun sendDialogMessageNotification(receiverId: Long, dialogNotification: DialogNotificationDto) {
        natsService.writeMessageForUser(receiverId, jsonHelper.toJson(dialogNotification))
    }
}