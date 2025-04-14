package br.com.api.crypto_chat.integration.coingecko;

import br.com.api.crypto_chat.integration.coingecko.response.CoinMarketData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(
    name = "coinGeckoClient", 
    url = "${crypto-chat.url.crypto-gecko}",
    configuration = CoinGeckoConfig.class
)
public interface CoinGeckoClient {

    // 🔄 Status Check
    @GetMapping("/ping")
    Map<String, String> ping();

    // 🔐 API Key Check
    @GetMapping("/key")
    Map<String, Object> checkApiKey();

    // 💰 Simple Price Endpoints
    @GetMapping("/simple/price")
    Map<String, Map<String, Double>> getSimplePrice(@RequestParam Map<String, String> query);

    @GetMapping("/simple/supported_vs_currencies")
    List<String> getSupportedVsCurrencies();

    // 📄 Coin Data Endpoints
    @GetMapping("/coins/list")
    List<Map<String, String>> getCoinsList();

    @GetMapping("/coins/markets")
    List<CoinMarketData> getCoinsMarket(@RequestParam Map<String, String> query);

    @GetMapping("/coins/{id}")
    Map<String, Object> getCoinDetails(@PathVariable("id") String coinId);

    @GetMapping("/coins/{id}/tickers")
    Map<String, Object> getCoinTickers(@PathVariable("id") String coinId);

    @GetMapping("/coins/{id}/history")
    Map<String, Object> getCoinHistory(
        @PathVariable("id") String coinId, 
        @RequestParam Map<String, String> query
    );

    @GetMapping("/coins/{id}/market_chart")
    Map<String, List<List<Number>>> getCoinMarketChart(
        @PathVariable("id") String coinId, 
        @RequestParam Map<String, String> query
    );

    @GetMapping("/coins/{id}/market_chart/range")
    Map<String, List<List<Number>>> getCoinMarketChartRange(
        @PathVariable("id") String coinId, 
        @RequestParam Map<String, String> query
    );

    // 🔍 Search and Categories
    @GetMapping("/search")
    Map<String, List<Map<String, Object>>> search(@RequestParam("query") String query);

    @GetMapping("/search/trending")
    Map<String, List<Map<String, Object>>> getTrendingSearches();

    @GetMapping("/coins/categories/list")
    List<Map<String, String>> getCoinsCategoriesList();

    @GetMapping("/coins/categories")
    List<Map<String, Object>> getCoinsCategories();

    // 🌍 Global Market Data
    @GetMapping("/global")
    Map<String, Object> getGlobalData();

    @GetMapping("/global/decentralized_finance_defi")
    Map<String, Object> getGlobalDefiData();

    // 🌐 Exchange Data
    @GetMapping("/exchanges")
    List<Map<String, Object>> getExchanges();

    @GetMapping("/exchanges/list")
    List<Map<String, String>> getExchangesList();

    @GetMapping("/exchanges/{id}")
    Map<String, Object> getExchangeDetails(@PathVariable("id") String exchangeId);

    // 💹 Derivatives
    @GetMapping("/derivatives")
    List<Map<String, Object>> getDerivatives();

    @GetMapping("/derivatives/exchanges")
    List<Map<String, Object>> getDerivativesExchanges();

    @GetMapping("/derivatives/exchanges/{id}")
    Map<String, Object> getDerivativesExchangeById(@PathVariable("id") String id);

    // 🧾 Asset Platforms and Exchange Rates
    @GetMapping("/asset_platforms")
    List<Map<String, Object>> getAssetPlatforms();

    @GetMapping("/exchange_rates")
    Map<String, Object> getExchangeRates();
}
