package br.com.api.crypto_chat.data.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.crypto_chat.data.entity.LogMessage;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, UUID> {

    /**
     * Find all messages by user login ordered by date ascending
     * @param login The user's login
     * @return List of messages
     */
    List<LogMessage> findAllByLoginOrderByDateMessageAsc(String login);

    /**
     * Find all messages by user login within a date range
     * @param login The user's login
     * @param start Start date (inclusive)
     * @param end End date (inclusive)
     * @return List of messages ordered by date
     */
    List<LogMessage> findAllByLoginAndDateMessageBetweenOrderByDateMessageAsc(
        String login, 
        LocalDateTime start, 
        LocalDateTime end
    );
}
