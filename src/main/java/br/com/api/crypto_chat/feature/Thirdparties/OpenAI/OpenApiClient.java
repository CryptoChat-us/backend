package br.com.api.crypto_chat.feature.Thirdparties.OpenAI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.crypto_chat.vo.ChatRequestVO;
import br.com.api.crypto_chat.feature.Thirdparties.OpenAI.vo.SpeechRequestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@FeignClient(
    name = "openAIClient",
    url = "${crypto-chat.url.open.api}",
    configuration = OpenAIConfig.class
)
@Tag(name = "OpenAI API", description = "Interface for OpenAI chat and speech services")
public interface OpenApiClient {

    @Operation(summary = "Generate chat completion")
    @PostMapping("/v1/chat/completions")
    String generateChatCompletion(@RequestBody ChatRequestVO request);

    @Operation(summary = "Generate speech from text")
    @PostMapping("/v1/audio/speech")
    ResponseEntity<byte[]> generateSpeech(@RequestBody SpeechRequestVO request);
}
