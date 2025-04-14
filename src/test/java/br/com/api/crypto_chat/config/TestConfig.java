
/*
package br.com.api.crypto_chat.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.api.crypto_chat.feature.Thirdparties.OpenAI.OpenApiService;
import br.com.api.crypto_chat.feature.Translation.TranslationService;
import br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap.CoinMarketCapClient;
import br.com.api.crypto_chat.integration.coingecko.CoinGeckoClient;
import br.com.api.crypto_chat.integration.coingecko.response.CoinMarketData;
import br.com.api.crypto_chat.integration.cryptopanic.CryptoPanicClient;
import br.com.api.crypto_chat.integration.cryptopanic.response.NewsResponse;
import br.com.api.crypto_chat.vo.ChatRequestVO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    @Primary
    public OpenApiService openApiService() {
        OpenApiService mockService = Mockito.mock(OpenApiService.class);
        
        // Mock response for chat completion
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode mockResponse = mapper.createObjectNode();
        ObjectNode choice = mapper.createObjectNode();
        ObjectNode message = mapper.createObjectNode();
        message.put("content", "Test response");
        choice.set("message", message);
        mockResponse.putArray("choices").add(choice);
        
        Mockito.when(mockService.generateChatCompletion(Mockito.any(ChatRequestVO.class)))
            .thenReturn(mockResponse);
            
        return mockService;
    }

    @Bean
    @Primary
    public TranslationService translationService() {
        TranslationService mockService = Mockito.mock(TranslationService.class);
        
        Mockito.when(mockService.translateText(Mockito.anyString(), Mockito.anyString()))
            .thenAnswer(invocation -> "Translated: " + invocation.getArgument(0));
            
        return mockService;
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public CoinMarketCapClient coinMarketCapClient() {
        CoinMarketCapClient mockClient = Mockito.mock(CoinMarketCapClient.class);
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "success");
        Mockito.when(mockClient.getLatestListings(Mockito.any()))
            .thenReturn(mockResponse);
        return mockClient;
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
*/