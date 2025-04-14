package br.com.api.crypto_chat.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Login is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Login must be 3-20 characters long and contain only letters, numbers, and underscores")
    private String login;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
             message = "Password must be at least 8 characters long and contain at least one letter and one number")
    private String password;

    private String language = "EN";
}
