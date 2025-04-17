package br.com.api.crypto_chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${crypto-chat.allowed-origin:https://crypto-chat.com}")
    private String allowedOrigin;

    private static final String CHAT_ENDPOINT = "/chat/info";
    private static final String TOPIC_PREFIX = "/topic";
    private static final String APP_PREFIX = "/app";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(TOPIC_PREFIX);
        config.setApplicationDestinationPrefixes(APP_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Configure WebSocket endpoint with session support
        registry.addEndpoint(CHAT_ENDPOINT)
                .setAllowedOrigins(allowedOrigin)
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .withSockJS()
                .setStreamBytesLimit(512 * 1024) // 512KB
                .setHttpMessageCacheSize(1000)
                .setDisconnectDelay(30 * 1000); // 30 seconds
    }
}
