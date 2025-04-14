package br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap;

import br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap.response.CMCCryptoData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/market/cmc")
@RequiredArgsConstructor
@Tag(name = "CoinMarketCap", description = "Endpoints for cryptocurrency data from CoinMarketCap")
public class CoinMarketCapController {

    private final CoinMarketCapService service;

    @GetMapping("/listings")
    @Operation(summary = "Get latest cryptocurrency listings")
    public ResponseEntity<List<CMCCryptoData>> getLatestListings(
            @RequestParam(defaultValue = "1") int start,
            @RequestParam(defaultValue = "100") int limit,
            @RequestParam(defaultValue = "USD") String convert) {
        return ResponseEntity.ok(service.getLatestListings(start, limit, convert));
    }

    @GetMapping("/quotes/{symbol}")
    @Operation(summary = "Get latest quotes for a specific cryptocurrency")
    public ResponseEntity<CMCCryptoData> getCryptoQuote(
            @PathVariable String symbol,
            @RequestParam(defaultValue = "USD") String convert) {
        return ResponseEntity.ok(service.getCryptoQuote(symbol, convert));
    }

    @GetMapping("/info/{symbol}")
    @Operation(summary = "Get metadata for a specific cryptocurrency")
    public ResponseEntity<Map<String, Object>> getCryptoInfo(
            @PathVariable String symbol) {
        return ResponseEntity.ok(service.getCryptoMetadata(symbol));
    }

    @GetMapping("/global")
    @Operation(summary = "Get global cryptocurrency market data")
    public ResponseEntity<Map<String, Object>> getGlobalMetrics() {
        return ResponseEntity.ok(service.getGlobalMetrics());
    }

    @GetMapping("/convert")
    @Operation(summary = "Convert cryptocurrency prices")
    public ResponseEntity<Map<String, Object>> convertPrice(
            @RequestParam String amount,
            @RequestParam String from,
            @RequestParam String to) {
        return ResponseEntity.ok(service.convertPrice(amount, from, to));
    }

    @GetMapping("/map/crypto")
    @Operation(summary = "Get cryptocurrency map")
    public ResponseEntity<List<Map<String, Object>>> getCryptoMap() {
        return ResponseEntity.ok(service.getCryptoMap());
    }

    @GetMapping("/map/fiat")
    @Operation(summary = "Get fiat currency map")
    public ResponseEntity<List<Map<String, Object>>> getFiatMap() {
        return ResponseEntity.ok(service.getFiatMap());
    }

    @GetMapping("/pairs/{symbol}")
    @Operation(summary = "Get market pairs for a specific cryptocurrency")
    public ResponseEntity<Map<String, Object>> getMarketPairs(
            @PathVariable String symbol) {
        return ResponseEntity.ok(service.getMarketPairs(symbol));
    }
}
