package br.com.api.crypto_chat.integration.coingecko;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoinGeckoConfig {

    @Value("${crypto-chat.api-key.crypto-gecko}")
    private String apiKey;

    @Bean
    public RequestInterceptor coinGeckoInterceptor() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("CoinGecko API key is not configured. Please set 'crypto-chat.api-key.crypto-gecko' in your configuration.");
        }

        return requestTemplate -> {
            requestTemplate.header("x-cg-pro-api-key", apiKey);
            requestTemplate.header("Accept", "application/json");
        };
    }
}
