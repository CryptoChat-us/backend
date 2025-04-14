package br.com.api.crypto_chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatMessageRequest {
    
    @NotBlank(message = "Login is required")
    private String login;

    @NotBlank(message = "Message is required")
    private String message;

    private String topic;
}
