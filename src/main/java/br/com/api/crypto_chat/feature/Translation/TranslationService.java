package br.com.api.crypto_chat.feature.Translation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.api.crypto_chat.feature.Thirdparties.OpenAI.OpenApiService;
import br.com.api.crypto_chat.utils.AIResponseSanitizer;
import br.com.api.crypto_chat.vo.ChatRequestVO;
import br.com.api.crypto_chat.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TranslationService {

    @Autowired
    OpenApiService openAIService;
    @Autowired
    AIResponseSanitizer aiResponseSanitizer;

    @Value("${openai.model.default:gpt-4}")
    private String defaultModel;

    /**
     * Translates text to the target language using the default AI model
     */
    public String translateText(String text, String targetLang) {
        return translateText(text, targetLang, defaultModel);
    }

    /**
     * Translates text to the target language using a specific AI model
     */
    public String translateText(String text, String targetLang, String model) {
        try {
            return translateViaAI(text, targetLang, model);
        } catch (Exception e) {
            log.error("Failed to translate text: {}", e.getMessage());
            throw new TranslationException("Failed to translate text", e);
        }
    }

    private String translateViaAI(String text, String targetLang, String model) {
        ChatRequestVO request = buildTranslationRequest(text, targetLang, model);
        JsonNode response = openAIService.generateChatCompletion(request);
        String translatedText = extractTranslatedText(response);
        return aiResponseSanitizer.sanitize(translatedText);
    }

    private ChatRequestVO buildTranslationRequest(String text, String targetLang, String model) {
        String prompt = String.format(
                "Translate the following text to %s. Return only the translated text without any additional formatting, explanation, or quotes:\n\n%s",
                targetLang, text);

        return ChatRequestVO.builder()
                .model(model)
                .messages(List.of(new MessageVO(prompt, "user")))
                .temperature(0.3)
                .build();
    }

    private String extractTranslatedText(JsonNode response) {
        try {
            return response.get("choices").get(0).get("message").get("content").asText();
        } catch (Exception e) {
            log.error("Failed to extract translated text from response: {}", e.getMessage());
            throw new TranslationException("Failed to process translation response", e);
        }
    }
}
