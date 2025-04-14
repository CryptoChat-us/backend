package br.com.api.crypto_chat.feature.Translation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/translation")
@Tag(name = "Translation", description = "Endpoints for text translation")
@RequiredArgsConstructor
@Validated
public class TranslationController {

    private final TranslationService translationService;

    @Operation(summary = "Translate text to target language")
    @PostMapping("/translate")
    public ResponseEntity<String> translateText(
            @Parameter(description = "Text to translate", required = true)
            @RequestParam String text,
            @Parameter(description = "Target language code (e.g., 'en', 'es', 'pt')", required = true)
            @RequestParam String targetLang,
            @Parameter(description = "OpenAI model to use (e.g., 'gpt-4', 'gpt-3.5-turbo'). If not provided, uses default model.")
            @RequestParam(required = false) String model) {
        String translatedText = model != null ? 
            translationService.translateText(text, targetLang, model) :
            translationService.translateText(text, targetLang);
        return ResponseEntity.ok(translatedText);
    }
}
