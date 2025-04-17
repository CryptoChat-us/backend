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
     * Find all messages by user email ordered by date ascending
     * 
     * @param email The user's email
     * @return List of messages
     */
    List<LogMessage> findAllByEmailOrderByDateMessageAsc(String email);

    /**
     * Find all messages by user email within a date range
     * 
     * @param email The user's email
     * @param start Start date (inclusive)
     * @param end   End date (inclusive)
     * @return List of messages ordered by date
     */
    List<LogMessage> findAllByEmailAndDateMessageBetweenOrderByDateMessageAsc(
            String email,
            LocalDateTime start,
            LocalDateTime end);
}
