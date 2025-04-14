package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CoinGecko;

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
public class CryptoApiService {

    private final CryptoApiClient cryptoApiClient;
    private final ObjectMapper objectMapper;

    @Retry(name = "cryptoGeckoRetry")
    public JsonNode getCoinPrice(String coinName) {
        try {
            String response = cryptoApiClient.getCoinPrice(coinName, "eur");
            return objectMapper.readTree(response);
        } catch (FeignException e) {
            log.error("Error calling CoinGecko API: {}", e.getMessage());
            throw new CryptoApiException("Failed to fetch coin price from CoinGecko", e);
        } catch (JsonProcessingException e) {
            log.error("Error parsing CoinGecko response: {}", e.getMessage());
            throw new CryptoApiException("Failed to parse CoinGecko response", e);
        }
    }
}
