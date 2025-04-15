package br.com.api.crypto_chat.integration.coingecko;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.crypto_chat.integration.coingecko.response.CoinMarketData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoinGeckoService {

    @Autowired
    CoinGeckoClient coinGeckoClient;

    public boolean isServiceHealthy() {
        try {
            Map<String, String> response = coinGeckoClient.ping();
            return "gecko_says_hello".equals(response.get("gecko_says"));
        } catch (Exception e) {
            log.error("Error checking CoinGecko service health", e);
            return false;
        }
    }

    public List<CoinMarketData> getTopCoins(int limit, String currency) {
        Map<String, String> params = new HashMap<>();
        params.put("vs_currency", currency.toLowerCase());
        params.put("order", "market_cap_desc");
        params.put("per_page", String.valueOf(limit));
        params.put("page", "1");
        params.put("sparkline", "false");

        return coinGeckoClient.getCoinsMarket(params);
    }

    public Map<String, Double> getCoinPrice(String coinId, String currency) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", coinId);
        params.put("vs_currencies", currency.toLowerCase());

        return coinGeckoClient.getSimplePrice(params).get(coinId);
    }

    public Map<String, List<List<Number>>> getCoinPriceHistory(String coinId, String currency, int days) {
        Map<String, String> params = new HashMap<>();
        params.put("vs_currency", currency.toLowerCase());
        params.put("days", String.valueOf(days));

        return coinGeckoClient.getCoinMarketChart(coinId, params);
    }

    public Map<String, Object> getGlobalMarketData() {
        return coinGeckoClient.getGlobalData();
    }

    public List<Map<String, Object>> searchCoins(String query) {
        return coinGeckoClient.search(query).get("coins");
    }

    public List<Map<String, Object>> getTrendingCoins() {
        return coinGeckoClient.getTrendingSearches().get("coins");
    }
}
