package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CoinGecko;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@FeignClient(
    name = "coinGeckoClient",
    url = "${crypto-chat.url.crypto-gecko}",
    configuration = CryptoApiConfig.class
)
@Tag(name = "CoinGecko API", description = "Interface for CoinGecko cryptocurrency price data")
public interface CryptoApiClient {

    @Operation(summary = "Get current price for a cryptocurrency")
    @GetMapping("/simple/price")
    String getCoinPrice(
        @RequestParam("ids") String coinIds,
        @RequestParam("vs_currencies") String currencies
    );
}