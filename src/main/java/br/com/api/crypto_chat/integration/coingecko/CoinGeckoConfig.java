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
        return requestTemplate -> {
            if (apiKey != null && !apiKey.isEmpty()) {
                requestTemplate.header("x-cg-pro-api-key", apiKey);
            }
            requestTemplate.header("Accept", "application/json");
        };
    }
}
