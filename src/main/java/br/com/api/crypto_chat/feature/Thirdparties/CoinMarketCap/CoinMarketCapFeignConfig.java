package br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoinMarketCapFeignConfig {
    
    @Value("${crypto-chat.api-key.coin-market-cap}")
    private String apiKey;

    @Bean
    public RequestInterceptor cmcRequestInterceptor() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("CoinMarketCap API key is not configured. Please set 'crypto-chat.api-key.coin-market-cap' in your configuration.");
        }

        return requestTemplate -> {
            requestTemplate.header("X-CMC_PRO_API_KEY", apiKey);
            requestTemplate.header("Accept", "application/json");
        };
    }
}
