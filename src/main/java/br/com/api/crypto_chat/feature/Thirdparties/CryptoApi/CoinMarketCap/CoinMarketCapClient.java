package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CoinMarketCap;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@FeignClient(
    name = "coinMarketCapClient",
    url = "${crypto-chat.url.coin-market-cap}",
    configuration = CoinMarketCapConfig.class
)
@Tag(name = "CoinMarketCap API", description = "Interface for CoinMarketCap cryptocurrency data")
public interface CoinMarketCapClient {

    @Operation(summary = "Get latest cryptocurrency listings")
    @GetMapping("/cryptocurrency/listings/latest")
    String getLatestListings();
}
