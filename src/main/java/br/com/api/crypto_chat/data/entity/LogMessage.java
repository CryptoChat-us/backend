package br.com.api.crypto_chat.data.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "TB_LOG_MESSAGE",
    indexes = {
        @Index(name = "idx_log_message_login", columnList = "login"),
        @Index(name = "idx_log_message_date", columnList = "dateMessage")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idLogMessage")
public class LogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID idLogMessage;

    @Column(nullable = false)
    private String login;

    @Lob
    @Column(nullable = false)
    private String message;

    @Lob
    @Column(nullable = false)
    private String messageResponse;

    @Column(nullable = false)
    private LocalDateTime dateMessage;

}
