package br.com.api.crypto_chat.integration.cryptopanic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class NewsResponse {
    private int count;
    private String next;
    private String previous;
    
    @JsonProperty("results")
    private List<NewsItem> newsItems;
}
