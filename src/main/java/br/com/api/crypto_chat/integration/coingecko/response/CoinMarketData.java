package br.com.api.crypto_chat.integration.coingecko.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CoinMarketData {
    private String id;
    private String symbol;
    private String name;
    private String image;
    
    @JsonProperty("current_price")
    private BigDecimal currentPrice;
    
    @JsonProperty("market_cap")
    private BigDecimal marketCap;
    
    @JsonProperty("market_cap_rank")
    private Integer marketCapRank;
    
    @JsonProperty("total_volume")
    private BigDecimal totalVolume;
    
    @JsonProperty("high_24h")
    private BigDecimal high24h;
    
    @JsonProperty("low_24h")
    private BigDecimal low24h;
    
    @JsonProperty("price_change_24h")
    private BigDecimal priceChange24h;
    
    @JsonProperty("price_change_percentage_24h")
    private BigDecimal priceChangePercentage24h;
    
    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;
    
    @JsonProperty("total_supply")
    private BigDecimal totalSupply;
    
    @JsonProperty("max_supply")
    private BigDecimal maxSupply;
    
    @JsonProperty("ath")
    private BigDecimal allTimeHigh;
    
    @JsonProperty("ath_date")
    private String athDate;
}
