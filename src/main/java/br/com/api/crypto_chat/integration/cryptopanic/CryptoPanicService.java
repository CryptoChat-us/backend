package br.com.api.crypto_chat.integration.cryptopanic;

import br.com.api.crypto_chat.integration.cryptopanic.response.NewsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoPanicService {

    private final CryptoPanicClient client;

    public NewsResponse getNews(String filter, String currencies, boolean metadata, boolean approved, boolean panicScore) {
        return client.getPostsCombined(
            currencies,
            filter,
            metadata,
            approved,
            panicScore,
            null,
            null,
            null
        );
    }

    public NewsResponse getNewsByRegion(String regions, String filter) {
        return client.getPostsCombined(
            null,
            filter,
            true,
            true,
            true,
            regions,
            null,
            null
        );
    }

    public NewsResponse getNewsByKind(String kind) {
        return client.getPostsCombined(
            null,
            null,
            true,
            true,
            true,
            null,
            kind,
            null
        );
    }

    public NewsResponse getLatestNews(int page) {
        return client.getPostsCombined(
            null,
            "rising",
            true,
            true,
            true,
            null,
            null,
            page
        );
    }

    public Object getPortfolio() {
        return client.getPortfolio();
    }
}
