package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CryptoPanic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import br.com.api.crypto_chat.exception.CryptoApiException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoPanicService {

    private final CryptoPanicClient cryptoPanicClient;
    private final ObjectMapper objectMapper;

    @Value("${crypto-chat.api-key.crypto-panic}")
    private String apiKey;

    @Retry(name = "cryptoPanicRetry")
    public JsonNode getCryptoNews(String coins) {
        try {
            String response = cryptoPanicClient.getCryptoNews(apiKey, coins);
            return objectMapper.readTree(response);
        } catch (FeignException e) {
            log.error("Error calling CryptoPanic API: {}", e.getMessage());
            throw new CryptoApiException("Failed to fetch crypto news from CryptoPanic", e);
        } catch (JsonProcessingException e) {
            log.error("Error parsing CryptoPanic response: {}", e.getMessage());
            throw new CryptoApiException("Failed to parse CryptoPanic response", e);
        }
    }

}
