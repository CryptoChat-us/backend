package br.com.api.crypto_chat.feature.Login;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.crypto_chat.data.entity.User;
import br.com.api.crypto_chat.data.repository.UserRepository;
import br.com.api.crypto_chat.dto.auth.LoginRequest;
import br.com.api.crypto_chat.dto.auth.RegisterRequest;
import br.com.api.crypto_chat.data.enums.SubscriptionPlan;
import br.com.api.crypto_chat.data.enums.UserRole;
import br.com.api.crypto_chat.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    protected boolean isLoginAvailable(String login) {
        return !userRepository.existsByLogin(login);
    }

    public String authenticateUser(LoginRequest request) {
        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
                
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        
        // Update user stats
        user.setNumClasses(user.getNumClasses() + 1);
        user.setLastLoginDate(LocalDateTime.now());
        userRepository.save(user);
        
        return jwtService.generateToken(user);
    }

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByLoginOrEmail(request.getLogin(), request.getEmail())) {
            throw new IllegalArgumentException("User already exists with this login or email");
        }
        
        User newUser = User.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(UserRole.USER)
                .subscriptionPlan(SubscriptionPlan.FREE)
                .language(request.getLanguage())
                .isActive(true)
                .numClasses(0L)
                .registerDate(LocalDateTime.now())
                .build();
                
        userRepository.save(newUser);
        
        return jwtService.generateToken(newUser);
    }

    public void deleteUser(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
        log.info("User deleted: {}", login);
    }
}
