package br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap;

import br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap.response.CMCCryptoData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinMarketCapService {

    private final CoinMarketCapClient client;
    private final ObjectMapper objectMapper;

    public List<CMCCryptoData> getLatestListings(int start, int limit, String convert) {
        Map<String, String> params = new HashMap<>();
        params.put("start", String.valueOf(start));
        params.put("limit", String.valueOf(limit));
        params.put("convert", convert.toUpperCase());
        
        Map<String, Object> response = client.getLatestListings(params);
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
        
        return data.stream()
                  .map(item -> objectMapper.convertValue(item, CMCCryptoData.class))
                  .toList();
    }

    public CMCCryptoData getCryptoQuote(String symbol, String convert) {
        Map<String, Object> response = client.getLatestQuotes(symbol.toUpperCase(), convert.toUpperCase());
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Map<String, Object> cryptoData = (Map<String, Object>) data.get(symbol.toUpperCase());
        
        return objectMapper.convertValue(cryptoData, CMCCryptoData.class);
    }

    public Map<String, Object> getCryptoMetadata(String symbol) {
        Map<String, Object> response = client.getCryptoInfo(symbol.toUpperCase());
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return (Map<String, Object>) data.get(symbol.toUpperCase());
    }

    public Map<String, Object> getGlobalMetrics() {
        Map<String, Object> response = client.getGlobalMetrics();
        return (Map<String, Object>) response.get("data");
    }

    public Map<String, Object> convertPrice(String amount, String from, String to) {
        Map<String, Object> response = client.convertPrice(amount, from.toUpperCase(), to.toUpperCase());
        return (Map<String, Object>) response.get("data");
    }

    public List<Map<String, Object>> getCryptoMap() {
        Map<String, Object> response = client.getCryptoMap("cmc_rank");
        return (List<Map<String, Object>>) response.get("data");
    }

    public List<Map<String, Object>> getFiatMap() {
        Map<String, Object> response = client.getFiatMap();
        return (List<Map<String, Object>>) response.get("data");
    }

    public Map<String, Object> getMarketPairs(String symbol) {
        Map<String, Object> response = client.getMarketPairs(symbol.toUpperCase());
        return (Map<String, Object>) response.get("data");
    }
}
