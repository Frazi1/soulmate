package com.soulmate.services

import io.nats.streaming.StreamingConnection
import io.nats.streaming.StreamingConnectionFactory
import io.nats.streaming.Subscription
import org.springframework.stereotype.Service

@Service
class NatsService {
    companion object {
        private const val USER_CHANNEL_PREFIX = "USER_"
    }

    private val connectionFactory = StreamingConnectionFactory("test-cluster", "server1")
    private val connection: StreamingConnection

    private val activeSubscriptions: ArrayList<Pair<Long, Subscription>> = ArrayList()

    init {
        connection = connectionFactory.createConnection()
    }

    private fun getUserChannelName(userId: Long): String = "$USER_CHANNEL_PREFIX$userId"

    fun writeMessageForUser(userId: Long, text: String) {
        connection.publish(getUserChannelName(userId), text.toByteArray())
    }

    fun subscribeForUserChannel(userId: Long, messageHandler: (String) -> Unit) {
        if (activeSubscriptions.firstOrNull { it.first == userId } != null) return
        val subscription = connection.subscribe(getUserChannelName(userId)) {
            messageHandler(String(it.data))
        }
        activeSubscriptions.add(Pair(userId, subscription))
    }

    fun unsunscribeFromUserChannel(userId: Long) {
        val pair = activeSubscriptions.firstOrNull { it.first == userId }
        pair?.let {
            activeSubscriptions.remove(it)
            it.second.close()
        }


    }

}