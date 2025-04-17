package br.com.api.crypto_chat.data.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.crypto_chat.data.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

    /**
     * Find a chat by user email
     * 
     * @param email The user's email
     * @return Optional containing the chat if found
     */
    Optional<Chat> findByEmail(String login);
}
