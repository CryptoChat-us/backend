package br.com.api.crypto_chat.utils;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AIResponseSanitizer {
    
    /**
     * Sanitizes AI response text by removing common artifacts and formatting
     * 
     * @param response Raw response from AI
     * @return Cleaned response text
     */
    public String sanitize(String response) {
        if (response == null || response.isEmpty()) {
            return "";
        }

        try {
            return response
                // Remove code blocks
                .replaceAll("```[\\w]*\\n?|```", "")
                // Remove single backticks
                .replaceAll("`", "")
                // Remove language tags
                .replaceAll("\\b(json|javascript|python|java):", "")
                // Remove common prefixes AI might add
                .replaceAll("(?i)^(translation:|translated text:|result:|output:)\\s*", "")
                // Remove extra whitespace
                .trim();
        } catch (Exception e) {
            log.error("Error sanitizing AI response: {}", e.getMessage());
            return response.trim();
        }
    }
}
