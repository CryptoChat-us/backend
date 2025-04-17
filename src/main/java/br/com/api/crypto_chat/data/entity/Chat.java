package br.com.api.crypto_chat.data.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CHAT")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idChat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID idChat;

    @Column(nullable = false)
    private Boolean flgHasPrompt;

    @Column(unique = true, nullable = false)
    private String email;

}
