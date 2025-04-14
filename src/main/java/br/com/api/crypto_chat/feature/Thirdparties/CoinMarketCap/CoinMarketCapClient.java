package br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(
    name = "coinMarketCapClient",
    url = "${crypto-chat.url.coin-market-cap}",
    configuration = CoinMarketCapFeignConfig.class
)
public interface CoinMarketCapClient {

    @GetMapping("/v1/cryptocurrency/listings/latest")
    Map<String, Object> getLatestListings(@RequestParam Map<String, String> params);

    @GetMapping("/v1/cryptocurrency/quotes/latest")
    Map<String, Object> getLatestQuotes(@RequestParam("symbol") String symbol, @RequestParam("convert") String convert);

    @GetMapping("/v1/cryptocurrency/info")
    Map<String, Object> getCryptoInfo(@RequestParam("symbol") String symbol);

    @GetMapping("/v1/cryptocurrency/map")
    Map<String, Object> getCryptoMap(@RequestParam(value = "sort", required = false) String sort);

    @GetMapping("/v1/cryptocurrency/market-pairs/latest")
    Map<String, Object> getMarketPairs(@RequestParam("symbol") String symbol);

    @GetMapping("/v1/global-metrics/quotes/latest")
    Map<String, Object> getGlobalMetrics();

    @GetMapping("/v1/exchange/map")
    Map<String, Object> getExchangeMap();

    @GetMapping("/v1/fiat/map")
    Map<String, Object> getFiatMap();

    @GetMapping("/v2/tools/price-conversion")
    Map<String, Object> convertPrice(
        @RequestParam("amount") String amount,
        @RequestParam("symbol") String symbol,
        @RequestParam("convert") String convert
    );
}
