package br.com.api.crypto_chat.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.crypto_chat.data.entity.Prompts;
import br.com.api.crypto_chat.data.repository.PromptRepository;

@Component
public class InitializeDatabase {

    private static final Logger logger = LoggerFactory.getLogger(InitializeDatabase.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PromptRepository promptRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        insertPrompts();
    }

    private void insertPrompts() {
        try {
            List<Prompts> prompts = promptRepository.findAll();
            if (prompts.isEmpty()) {
                String promptsContent = getPrompts();
                if (promptsContent != null) {
                    JsonNode jsonValues = objectMapper.readTree(promptsContent);
                    List<Prompts> listPrompts = formatToPrompts(jsonValues);
                    insertPrompts(listPrompts);
                    logger.info("Successfully initialized prompts database");
                }
            }
        } catch (Exception e) {
            logger.error("Error initializing prompts database", e);
        }
    }

    private String getPrompts() {
        try {
            var resource = new ClassPathResource("prompts.txt");
            byte[] content = StreamUtils.copyToByteArray(resource.getInputStream());
            String str = new String(content, StandardCharsets.UTF_8);
            return new String(Base64.getDecoder().decode(str));
        } catch (IOException e) {
            logger.error("Error reading prompts file", e);
            return null;
        }
    }

    private List<Prompts> formatToPrompts(JsonNode jsonValues) {
        List<Prompts> list = new ArrayList<>();
        for (JsonNode node : jsonValues.get("prompts")) {
            Prompts prompt = new Prompts();
            prompt.setMessage(node.get("user").asText());
            prompt.setMessageResponse(node.get("assistant").asText());
            prompt.setDateMessage(LocalDateTime.now());
            list.add(prompt);
        }
        return list;
    }

    private void insertPrompts(List<Prompts> listPrompts) {
        for (Prompts prompt : listPrompts) {
            promptRepository.save(prompt);
        }
    }
}
