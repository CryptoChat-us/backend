package br.com.api.crypto_chat.feature.Thirdparties.OpenAI;

public class OpenAIException extends RuntimeException {
    
    public OpenAIException(String message) {
        super(message);
    }
    
    public OpenAIException(String message, Throwable cause) {
        super(message, cause);
    }
}
