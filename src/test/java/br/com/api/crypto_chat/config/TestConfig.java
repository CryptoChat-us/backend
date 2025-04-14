package br.com.api.crypto_chat.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import br.com.api.crypto_chat.feature.Thirdparties.OpenAI.OpenApiService;
import br.com.api.crypto_chat.feature.Translation.TranslationService;
import br.com.api.crypto_chat.vo.ChatRequestVO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@TestConfiguration
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
}
