package br.com.api.crypto_chat.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    EN("English"),
    PT("Portuguese"),
    ES("Spanish");
    
    private final String displayName;
    
    public static Language fromCode(String code) {
        if (code == null) return EN; // Default to English
        try {
            return Language.valueOf(code.toUpperCase());
        } catch (IllegalArgumentException e) {
            return EN; // Fallback to English if invalid code
        }
    }
}
