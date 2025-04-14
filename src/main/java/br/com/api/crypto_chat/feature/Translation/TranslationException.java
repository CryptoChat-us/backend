package br.com.api.crypto_chat.feature.Translation;

public class TranslationException extends RuntimeException {
    
    public TranslationException(String message) {
        super(message);
    }
    
    public TranslationException(String message, Throwable cause) {
        super(message, cause);
    }
}
