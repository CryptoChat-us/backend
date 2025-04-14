package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CoinMarketCap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CoinMarketCapConfig {
    
    @Value("${crypto-chat.api-key.coin-market-cap}")
    private String apiKey;

    @Bean
    public RequestInterceptor coinMarketCapRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-CMC_PRO_API_KEY", apiKey);
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }
}
