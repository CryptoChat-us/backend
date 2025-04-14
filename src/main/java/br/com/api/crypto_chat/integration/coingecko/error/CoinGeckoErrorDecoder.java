package br.com.api.crypto_chat.integration.coingecko.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Component
public class CoinGeckoErrorDecoder implements ErrorDecoder {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String message = getErrorMessage(response);

        switch (status) {
            case TOO_MANY_REQUESTS:
                return new ResponseStatusException(status, "Rate limit exceeded: " + message);
            case UNAUTHORIZED:
                return new ResponseStatusException(status, "Invalid API key or unauthorized access: " + message);
            case NOT_FOUND:
                return new ResponseStatusException(status, "Resource not found: " + message);
            case BAD_REQUEST:
                return new ResponseStatusException(status, "Invalid request parameters: " + message);
            default:
                return new ResponseStatusException(status, "CoinGecko API error: " + message);
        }
    }

    private String getErrorMessage(Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            Map<String, Object> error = objectMapper.readValue(bodyIs, Map.class);
            return error.getOrDefault("error", "Unknown error").toString();
        } catch (IOException e) {
            log.error("Error parsing CoinGecko error response", e);
            return "Could not parse error message";
        }
    }
}
