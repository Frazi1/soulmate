package com.soulmate.config

import com.soulmate.controller.websocket.NotificationsWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import java.util.concurrent.Executors




@EnableWebSocket
@Configuration
class WebSocketConfig(private var notificationsWebSocketHandler: NotificationsWebSocketHandler) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
                .addHandler(notificationsWebSocketHandler, "/notifications")
                .setAllowedOrigins("*")
    }

    @Bean
    fun taskScheduler(): TaskScheduler {
        return ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor())
    }
}