package br.com.api.crypto_chat.feature.Login;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.api.crypto_chat.config.JwtUtils;
import br.com.api.crypto_chat.data.entity.User;
import br.com.api.crypto_chat.data.enums.SubscriptionPlan;
import br.com.api.crypto_chat.data.enums.UserRole;
import br.com.api.crypto_chat.data.repository.UserRepository;
import br.com.api.crypto_chat.dto.auth.LoginRequest;
import br.com.api.crypto_chat.dto.auth.RegisterRequest;
import br.com.api.crypto_chat.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtService;

    protected boolean isLoginAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public String authenticateUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Update user stats
        user.setLastLoginDate(LocalDateTime.now());
        user = userRepository.save(user);

        return jwtService.generateToken(user);
    }

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User already exists with this login or email");
        }

        User newUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .subscriptionPlan(SubscriptionPlan.FREE)
                .language(request.getLanguage())
                .isActive(true)
                .registerDate(LocalDateTime.now())
                .build();

        userRepository.save(newUser);

        return jwtService.generateToken(newUser);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
        log.info("User deleted: {}", email);
    }
}
