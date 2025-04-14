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
    name = "TB_PERFIL_INVESTIDOR",
    indexes = {
        @Index(name = "idx_perfil_investidor_login", columnList = "login"),
        @Index(name = "idx_perfil_investidor_data", columnList = "dataConclusao")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPerfil")
public class PerfilInvestidor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID idPerfil;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false, length = 50)
    private String classificacao;

    @Lob
    @Column(nullable = false)
    private String descricao;

    @Lob
    @Column(nullable = false)
    private String estrategiaSugerida;

    @Column(nullable = false)
    private LocalDateTime dataConclusao;
}
