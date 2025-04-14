package br.com.api.crypto_chat.data.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PROMPT_TREINAMENTO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPrompt")
public class PromptTreinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID idPrompt;

    @Column(nullable = false)
    private String fluxo;

    @Column(nullable = false)
    private String intencao;

    @Lob
    @Column(nullable = false)
    private String inputUsuario;

    @Lob
    @Column(nullable = false)
    private String respostaEsperada;

    @Column(nullable = false)
    private String tipoResposta;

    @Column(nullable = false)
    private String tomResposta;

    @Column(nullable = false)
    private String linguagem;

    @Column(nullable = false)
    private boolean geraAcao;

    @Column(nullable = false)
    private boolean contextoGlobal;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;
}
