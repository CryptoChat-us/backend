package br.com.api.crypto_chat.feature.Thirdparties.OpenAI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OpenAIConfig {
    
    @Value("${crypto-chat.url.open.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor openAIRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + apiKey);
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
