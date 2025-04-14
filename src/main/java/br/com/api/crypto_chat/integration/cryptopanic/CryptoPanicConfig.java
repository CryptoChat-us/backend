package br.com.api.crypto_chat.integration.cryptopanic;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoPanicConfig {
    
    @Value("${crypto-chat.api-key.crypto-panic}")
    private String apiKey;

    @Bean
    public RequestInterceptor cryptoPanicInterceptor() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("CryptoPanic API key is not configured. Please set 'crypto-chat.api-key.crypto-panic' in your configuration.");
        }

        return requestTemplate -> {
            requestTemplate.query("auth_token", apiKey);
            requestTemplate.header("Accept", "application/json");
        };
    }
}
