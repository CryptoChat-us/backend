package br.com.api.crypto_chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final JWTFilter filtroJwt;

    public SecurityConfig(JWTFilter filtroJwt) {
        this.filtroJwt = filtroJwt;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF não necessário para JWT
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .requestMatchers(
                        "/api/v1/auth/verify-login",
                        "/api/v1/auth/register",
                        "/api/v1/auth/login")
                .permitAll() // Permite o acesso sem autenticação para login e registro
                .anyRequest().authenticated() // Requer autenticação para outras rotas
                .and()
                .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT antes
                                                                                         // do filtro de autenticação
                                                                                         // padrão

        return http.build(); // Retorna o SecurityFilterChain configurado
    }

}
