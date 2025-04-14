package br.com.api.crypto_chat.feature.Thirdparties.CoinMarketCap.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class CMCCryptoData {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    
    @JsonProperty("cmc_rank")
    private Integer cmcRank;
    
    @JsonProperty("num_market_pairs")
    private Integer numMarketPairs;
    
    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;
    
    @JsonProperty("total_supply")
    private BigDecimal totalSupply;
    
    @JsonProperty("max_supply")
    private BigDecimal maxSupply;
    
    @JsonProperty("last_updated")
    private String lastUpdated;
    
    @JsonProperty("date_added")
    private String dateAdded;
    
    private Map<String, CMCQuote> quote;
    
    private Map<String, String> urls;
    private String logo;
    private String description;
    
    @JsonProperty("platform")
    private Map<String, Object> platform;
}
