package br.com.api.crypto_chat.feature.Thirdparties.CryptoApi.CryptoPanic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@FeignClient(
    name = "cryptoPanicClient",
    url = "${crypto-chat.url.crypto-panic}",
    configuration = CryptoPanicConfig.class
)
@Tag(name = "CryptoPanic API", description = "Interface for CryptoPanic cryptocurrency news data")
public interface CryptoPanicClient {

    @Operation(summary = "Get cryptocurrency news")
    @GetMapping("/free/v1/posts/")
    String getCryptoNews(
        @RequestParam("auth_token") String authToken,
        @RequestParam("currencies") String currencies
    );
}
