package br.com.api.crypto_chat.data.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.crypto_chat.data.entity.PerfilInvestidor;

@Repository
public interface PerfilInvestidorRepository extends JpaRepository<PerfilInvestidor, UUID> {

    /**
     * Finds the most recent investor profile for a given user
     * 
     * @param login The user's login
     * @return Optional containing the most recent profile if exists
     */
    Optional<PerfilInvestidor> findTopByLoginOrderByDataConclusaoDesc(String login);
}
