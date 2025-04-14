package br.com.api.crypto_chat.controller;

import br.com.api.crypto_chat.integration.coingecko.CoinGeckoService;
import br.com.api.crypto_chat.integration.coingecko.response.CoinMarketData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
@Tag(name = "Cryptocurrency", description = "Endpoints for cryptocurrency data from CoinGecko")
public class CoinGeckoController {

    private final CoinGeckoService coinGeckoService;

    @GetMapping("/status")
    @Operation(summary = "Check CoinGecko API status")
    public ResponseEntity<Map<String, Boolean>> getApiStatus() {
        boolean isHealthy = coinGeckoService.isServiceHealthy();
        return ResponseEntity.ok(Map.of("healthy", isHealthy));
    }

    @GetMapping("/coins/top")
    @Operation(summary = "Get top cryptocurrencies by market cap")
    public ResponseEntity<List<CoinMarketData>> getTopCoins(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "usd") String currency) {
        return ResponseEntity.ok(coinGeckoService.getTopCoins(limit, currency));
    }

    @GetMapping("/coin/{id}/price")
    @Operation(summary = "Get current price for a specific coin")
    public ResponseEntity<Map<String, Double>> getCoinPrice(
            @PathVariable String id,
            @RequestParam(defaultValue = "usd") String currency) {
        return ResponseEntity.ok(coinGeckoService.getCoinPrice(id, currency));
    }

    @GetMapping("/coin/{id}/chart")
    @Operation(summary = "Get historical price data for a coin")
    public ResponseEntity<Map<String, List<List<Number>>>> getCoinChart(
            @PathVariable String id,
            @RequestParam(defaultValue = "usd") String currency,
            @RequestParam(defaultValue = "7") int days) {
        return ResponseEntity.ok(coinGeckoService.getCoinPriceHistory(id, currency, days));
    }

    @GetMapping("/market/global")
    @Operation(summary = "Get global cryptocurrency market data")
    public ResponseEntity<Map<String, Object>> getGlobalMarketData() {
        return ResponseEntity.ok(coinGeckoService.getGlobalMarketData());
    }

    @GetMapping("/search")
    @Operation(summary = "Search for coins")
    public ResponseEntity<List<Map<String, Object>>> searchCoins(
            @RequestParam String query) {
        return ResponseEntity.ok(coinGeckoService.searchCoins(query));
    }

    @GetMapping("/trending")
    @Operation(summary = "Get trending coins")
    public ResponseEntity<List<Map<String, Object>>> getTrendingCoins() {
        return ResponseEntity.ok(coinGeckoService.getTrendingCoins());
    }
}
