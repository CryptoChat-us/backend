package br.com.api.crypto_chat.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.crypto_chat.data.entity.Prompts;

@Repository
public interface PromptRepository extends JpaRepository<Prompts, Long> {
}
