package br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CMCQuote {
    private BigDecimal price;
    
    @JsonProperty("volume_24h")
    private BigDecimal volume24h;
    
    @JsonProperty("volume_change_24h")
    private BigDecimal volumeChange24h;
    
    @JsonProperty("percent_change_1h")
    private BigDecimal percentChange1h;
    
    @JsonProperty("percent_change_24h")
    private BigDecimal percentChange24h;
    
    @JsonProperty("percent_change_7d")
    private BigDecimal percentChange7d;
    
    @JsonProperty("market_cap")
    private BigDecimal marketCap;
    
    @JsonProperty("market_cap_dominance")
    private BigDecimal marketCapDominance;
    
    @JsonProperty("last_updated")
    private String lastUpdated;
}
