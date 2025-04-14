package br.com.api.crypto_chat.feature.Thirdparties.OpenAI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.crypto_chat.vo.ChatRequestVO;
import br.com.api.crypto_chat.feature.Thirdparties.OpenAI.vo.SpeechRequestVO;
import feign.FeignException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenApiService {

    private final OpenApiClient openAIClient;
    private final ObjectMapper objectMapper;

    @Retry(name = "openAIRetry")
    public JsonNode callOpenAI(ChatRequestVO request) {
        return generateChatCompletion(request);
    }

    @Retry(name = "openAIRetry")
    public JsonNode generateChatCompletion(ChatRequestVO request) {
        try {
            String response = openAIClient.generateChatCompletion(request);
            return objectMapper.readTree(response);
        } catch (FeignException e) {
            log.error("Error calling OpenAI Chat API: {}", e.getMessage());
            throw new OpenAIException("Failed to generate chat completion", e);
        } catch (JsonProcessingException e) {
            log.error("Error parsing OpenAI response: {}", e.getMessage());
            throw new OpenAIException("Failed to parse OpenAI response", e);
        }
    }

    @Retry(name = "openAIRetry")
    public ResponseEntity<byte[]> generateSpeech(String text) {
        try {
            SpeechRequestVO request = SpeechRequestVO.builder()
                .model("tts-1")
                .input(text)
                .voice("alloy")
                .build();

            return openAIClient.generateSpeech(request);
        } catch (FeignException e) {
            log.error("Error calling OpenAI Speech API: {}", e.getMessage());
            throw new OpenAIException("Failed to generate speech", e);
        }
    }

}
