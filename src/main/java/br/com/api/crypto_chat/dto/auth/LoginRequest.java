package br.com.api.crypto_chat.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Login is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String language = "EN";
}
