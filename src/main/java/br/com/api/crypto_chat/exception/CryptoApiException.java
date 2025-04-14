package br.com.api.crypto_chat.exception;

public class CryptoApiException extends RuntimeException {
    public CryptoApiException(String message) {
        super(message);
    }

    public CryptoApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
