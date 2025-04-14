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
        return requestTemplate -> {
            requestTemplate.query("auth_token", apiKey);
            requestTemplate.header("Accept", "application/json");
        };
    }
}
