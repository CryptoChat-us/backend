package br.com.api.crypto_chat.data.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.crypto_chat.data.entity.PromptTreinamento;

@Repository
public interface PromptTreinamentoRepository extends JpaRepository<PromptTreinamento, UUID> {
    
    /**
     * Find all training prompts for a specific flow, ordered by creation date
     * @param fluxo The flow identifier
     * @return List of prompts ordered by creation date ascending
     */
    List<PromptTreinamento> findAllByFluxoOrderByDataCriacaoAsc(String fluxo);

    /**
     * Find training prompts by intention
     * @param intencao The intention to search for
     * @return List of prompts matching the intention
     */
    List<PromptTreinamento> findByIntencao(String intencao);
}
