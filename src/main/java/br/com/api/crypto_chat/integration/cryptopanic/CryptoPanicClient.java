package br.com.api.crypto_chat.integration.cryptopanic;

import br.com.api.crypto_chat.integration.cryptopanic.response.NewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "cryptoPanicClient", 
    url = "${crypto-chat.url.crypto-panic}",
    configuration = CryptoPanicConfig.class
)
public interface CryptoPanicClient {

    @GetMapping("/v1/posts/")
    NewsResponse getAllPosts();

    @GetMapping("/v1/posts/")
    NewsResponse getPostsByFilter(@RequestParam("filter") String filter);

    @GetMapping("/v1/posts/")
    NewsResponse getPostsByCurrencies(@RequestParam("currencies") String currencies);

    @GetMapping("/v1/posts/")
    NewsResponse getPostsByRegion(@RequestParam("regions") String regions);

    @GetMapping("/v1/posts/")
    NewsResponse getPostsByKind(@RequestParam("kind") String kind);

    @GetMapping("/v1/posts/")
    NewsResponse getPostsWithMetadata(@RequestParam("metadata") boolean metadata);

    @GetMapping("/v1/posts/")
    NewsResponse getPostsWithPanicScore(@RequestParam("panic_score") boolean panicScore);

    @GetMapping("/v1/posts/")
    NewsResponse getApprovedSourcePosts(@RequestParam("approved") boolean approved);

    @GetMapping("/v1/posts/")
    NewsResponse getPostsFollowing(@RequestParam("following") boolean following);

    @GetMapping("/v1/portfolio/")
    Object getPortfolio();

    @GetMapping("/v1/posts/")
    NewsResponse getPostsCombined(
        @RequestParam(value = "currencies", required = false) String currencies,
        @RequestParam(value = "filter", required = false) String filter,
        @RequestParam(value = "metadata", required = false) boolean metadata,
        @RequestParam(value = "approved", required = false) boolean approved,
        @RequestParam(value = "panic_score", required = false) boolean panicScore,
        @RequestParam(value = "regions", required = false) String regions,
        @RequestParam(value = "kind", required = false) String kind,
        @RequestParam(value = "page", required = false) Integer page
    );
}
