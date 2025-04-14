package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CoinMarketCap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.crypto_chat.exception.CryptoApiException;
import feign.FeignException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinMarketCapService {

    private final CoinMarketCapClient coinMarketCapClient;
    private final ObjectMapper objectMapper;

    @Retry(name = "coinMarketCapRetry")
    public JsonNode getLatestListings() {
        try {
            String response = coinMarketCapClient.getLatestListings();
            return objectMapper.readTree(response);
        } catch (FeignException e) {
            log.error("Error calling CoinMarketCap API: {}", e.getMessage());
            throw new CryptoApiException("Failed to fetch latest listings from CoinMarketCap", e);
        } catch (JsonProcessingException e) {
            log.error("Error parsing CoinMarketCap response: {}", e.getMessage());
            throw new CryptoApiException("Failed to parse CoinMarketCap response", e);
        }
    }
}
