package br.com.api.crypto_chat.integration.cryptopanic;

import br.com.api.crypto_chat.integration.cryptopanic.response.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Tag(name = "Crypto News", description = "Endpoints for cryptocurrency news and market sentiment")
public class CryptoPanicController {

    private final CryptoPanicService service;

    @GetMapping
    @Operation(summary = "Get cryptocurrency news with filters")
    public ResponseEntity<NewsResponse> getNews(
            @RequestParam(defaultValue = "rising") String filter,
            @RequestParam(defaultValue = "BTC,ETH") String currencies,
            @RequestParam(defaultValue = "true") boolean metadata,
            @RequestParam(defaultValue = "true") boolean approved,
            @RequestParam(defaultValue = "true") boolean panicScore) {
        return ResponseEntity.ok(service.getNews(filter, currencies, metadata, approved, panicScore));
    }

    @GetMapping("/region")
    @Operation(summary = "Get news by region")
    public ResponseEntity<NewsResponse> getNewsByRegion(
            @RequestParam String regions,
            @RequestParam(defaultValue = "rising") String filter) {
        return ResponseEntity.ok(service.getNewsByRegion(regions, filter));
    }

    @GetMapping("/kind")
    @Operation(summary = "Get news by kind (e.g., news, media)")
    public ResponseEntity<NewsResponse> getNewsByKind(
            @RequestParam String kind) {
        return ResponseEntity.ok(service.getNewsByKind(kind));
    }

    @GetMapping("/latest")
    @Operation(summary = "Get latest news with pagination")
    public ResponseEntity<NewsResponse> getLatestNews(
            @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(service.getLatestNews(page));
    }

    @GetMapping("/portfolio")
    @Operation(summary = "Get user portfolio data")
    public ResponseEntity<Object> getPortfolio() {
        return ResponseEntity.ok(service.getPortfolio());
    }
}
