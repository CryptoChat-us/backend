package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CoinGecko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CryptoApiConfig {
    
    @Bean
    public RequestInterceptor cryptoApiRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }
}
