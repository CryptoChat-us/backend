package br.com.api.crypto_chat.feature.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.crypto_chat.integration.cryptopanic.CryptoPanicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/news")
@Tag(name = "Crypto News", description = "Endpoints for cryptocurrency news")
@Validated
public class CryptoNewsController {

    @Autowired
    CryptoPanicService cryptoPanicService;

    @Operation(summary = "Get latest news for specific cryptocurrencies")
    @GetMapping("/crypto")
    public ResponseEntity<Object> getCryptoNews(
            @Parameter(description = "Comma-separated list of cryptocurrency symbols (e.g., 'BTC,ETH')", required = true) @RequestParam String coins) {
        return ResponseEntity.ok(cryptoPanicService.getNews("rising", coins, true, true, true));
    }
}
