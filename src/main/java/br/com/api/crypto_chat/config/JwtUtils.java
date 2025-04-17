package br.com.api.crypto_chat.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.api.crypto_chat.data.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    // Método para gerar o JWT
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getPassword())
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Método para extrair o username do JWT
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Método para extrair as Claims do JWT (informações do payload)
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para verificar se o token está expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    // Método para validar o token
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
