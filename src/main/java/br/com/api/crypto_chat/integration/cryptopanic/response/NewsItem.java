package br.com.api.crypto_chat.integration.cryptopanic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class NewsItem {
    private String kind;
    private String domain;
    private String title;
    private String url;
    
    @JsonProperty("published_at")
    private String publishedAt;
    
    @JsonProperty("slug")
    private String slug;
    
    private List<String> currencies;
    private Map<String, Object> metadata;
    
    @JsonProperty("created_at")
    private String createdAt;
    
    private Source source;
    
    @JsonProperty("vote_count")
    private Integer voteCount;
    
    @JsonProperty("positive_vote_count")
    private Integer positiveVoteCount;
    
    @JsonProperty("negative_vote_count")
    private Integer negativeVoteCount;
    
    @JsonProperty("panic_score")
    private Double panicScore;
    
    @Data
    public static class Source {
        private String title;
        private String region;
        private String domain;
        private String path;
    }
}
