package br.com.api.crypto_chat.integration.coingecko.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class GlobalMarketData {
    @JsonProperty("active_cryptocurrencies")
    private Integer activeCryptocurrencies;

    @JsonProperty("upcoming_icos")
    private Integer upcomingIcos;

    @JsonProperty("ongoing_icos")
    private Integer ongoingIcos;

    @JsonProperty("ended_icos")
    private Integer endedIcos;

    @JsonProperty("markets")
    private Integer markets;

    @JsonProperty("total_market_cap")
    private Map<String, BigDecimal> totalMarketCap;

    @JsonProperty("total_volume")
    private Map<String, BigDecimal> totalVolume;

    @JsonProperty("market_cap_percentage")
    private Map<String, BigDecimal> marketCapPercentage;

    @JsonProperty("market_cap_change_percentage_24h_usd")
    private BigDecimal marketCapChangePercentage24hUsd;

    @JsonProperty("updated_at")
    private Long updatedAt;
}
