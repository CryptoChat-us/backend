package br.com.api.crypto_chat.feature.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.crypto_chat.dto.auth.AuthResponse;
import br.com.api.crypto_chat.dto.auth.LoginRequest;
import br.com.api.crypto_chat.dto.auth.RegisterRequest;
import br.com.api.crypto_chat.enums.Language;
import br.com.api.crypto_chat.feature.Translation.TranslationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "Authentication", description = "APIs for user authentication and registration")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    TranslationService translationService;

    private static final String MESSAGE_ACCOUNT_CREATED = "Your account has been created successfully";
    private static final String MESSAGE_LOGIN_SUCCESS = "You have been logged in successfully";
    private static final String MESSAGE_USER_EXISTS = "User already exists with this login or email";
    private static final String MESSAGE_LOGIN_FAILED = "Invalid login credentials";

    @Operation(summary = "Check if a login is available")
    @GetMapping("/verify-login")
    public ResponseEntity<AuthResponse> verifyLogin(@RequestParam String login) {
        boolean available = authService.isLoginAvailable(login);
        return ResponseEntity.ok(AuthResponse.builder()
                .success(available)
                .message(available ? "Login is available" : "Login is already taken")
                .build());
    }

    @Operation(summary = "Authenticate a user")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            String token = authService.authenticateUser(request);
            String message = getTranslatedMessage(MESSAGE_LOGIN_SUCCESS, request.getLanguage());

            return ResponseEntity.ok(AuthResponse.builder()
                    .success(true)
                    .message(message)
                    .token(token)
                    .build());
        } catch (Exception e) {
            log.error("Login failed for user: {}", request.getLogin(), e);
            return ResponseEntity.badRequest().body(AuthResponse.error(MESSAGE_LOGIN_FAILED));
        }
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            String token = authService.registerUser(request);
            String message = getTranslatedMessage(MESSAGE_ACCOUNT_CREATED, request.getLanguage());

            return ResponseEntity.ok(AuthResponse.builder()
                    .success(true)
                    .message(message)
                    .token(token)
                    .build());
        } catch (Exception e) {
            log.error("Registration failed for user: {}", request.getLogin(), e);
            return ResponseEntity.badRequest().body(AuthResponse.error(MESSAGE_USER_EXISTS));
        }
    }

    @Operation(summary = "Delete a user account")
    @DeleteMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or #login == authentication.name")
    public ResponseEntity<AuthResponse> deleteUser(@RequestParam String login) {
        authService.deleteUser(login);
        return ResponseEntity.ok(AuthResponse.success("User deleted successfully"));
    }

    private String getTranslatedMessage(String message, String languageCode) {
        Language language = Language.fromCode(languageCode);
        if (language == Language.EN) {
            return message;
        }
        try {
            return translationService.translateText(message, language.name());
        } catch (Exception e) {
            log.error("Translation failed for language: {}", language, e);
            return message; // Fallback to English
        }
    }

}
