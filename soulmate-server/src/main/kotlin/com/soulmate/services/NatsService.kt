package com.soulmate.services

import io.nats.streaming.*
import org.springframework.stereotype.Service

@Service
class NatsService {
    companion object {
        private const val USER_CHANNEL_PREFIX = "USER_"
    }

    private val connectionFactory = StreamingConnectionFactory("test-cluster", "server1")
    private val connection: StreamingConnection

    private val activeSubscriptions: ArrayList<Triple<Long, Subscription, StreamingConnection>> = ArrayList()

    init {
        connection = connectionFactory.createConnection()
    }

    private fun getUserChannelName(userId: Long): String = "$USER_CHANNEL_PREFIX$userId"

    fun writeMessageForUser(userId: Long, text: String) {
        connection.publish(getUserChannelName(userId), text.toByteArray())
    }

    fun subscribeForUserChannel(userId: Long, messageHandler: (String) -> Unit) {
        if (activeSubscriptions.firstOrNull { it.first == userId } != null) return
        val userConnection = StreamingConnectionFactory("test-cluster", getUserChannelName(userId).plus("client"))
                .createConnection()
        val subscription = userConnection.subscribe(getUserChannelName(userId),
                { msg: Message ->
                    messageHandler(String(msg.data))
                },
                SubscriptionOptions.Builder()
                        .durableName(getUserChannelName(userId).plus("durable"))
//                        .manualAcks()
//                        .deliverAllAvailable()
                        .build())
        activeSubscriptions.add(Triple(userId, subscription, userConnection))
    }

    fun unsubscribeFromUserChannel(userId: Long) {
        val pair = activeSubscriptions.firstOrNull { it.first == userId }
        pair?.let {
            activeSubscriptions.remove(it)
            it.third.close()
//            it.second.close()
        }
    }

}