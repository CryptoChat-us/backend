package br.com.api.crypto_chat.feature.ChatBot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.api.crypto_chat.data.entity.Chat;
import br.com.api.crypto_chat.data.entity.LogMessage;
import br.com.api.crypto_chat.data.entity.Prompts;
import br.com.api.crypto_chat.data.repository.ChatRepository;
import br.com.api.crypto_chat.data.repository.LogMessageRepository;
import br.com.api.crypto_chat.data.repository.PromptRepository;
import br.com.api.crypto_chat.dto.ChatMessageRequest;
import br.com.api.crypto_chat.dto.ChatMessageResponse;
import br.com.api.crypto_chat.feature.Thirdparties.OpenAI.OpenApiService;
import br.com.api.crypto_chat.vo.ChatRequestVO;
import br.com.api.crypto_chat.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatBotService {

    private final LogMessageRepository logMessageRepository;
    private final OpenApiService openApiService;
    private final ChatRepository chatRepository;
    private final PromptRepository promptRepository;
    
    private static final String GPT_MODEL = "gpt-4-turbo-preview";

    public ChatMessageResponse processMessage(ChatMessageRequest request) {
        log.debug("Processing message from user: {}", request.getLogin());
        
        String response = processChat(request);
        recordLogMessage(request.getLogin(), request.getMessage(), response);
        
        return ChatMessageResponse.builder()
                .login(request.getLogin())
                .message(request.getMessage())
                .response(response)
                .topic(request.getTopic())
                .timestamp(LocalDateTime.now())
                .build();
    }

    private String processChat(ChatMessageRequest request) {
        // Prepare and send request
        List<MessageVO> messages = new ArrayList<>();

        // Get chat history or initialize with prompts
        Optional<Chat> chat = chatRepository.findByLogin(request.getLogin());
        if (chat.isEmpty() || !chat.get().getFlgHasPrompt()) {
            messages.addAll(loadPrimaryPrompts(request.getLogin()));
            initializeChat(request.getLogin());
        }

        // Add chat history
        logMessageRepository.findAllByLoginOrderByDateMessageAsc(request.getLogin())
                .forEach(msg -> {
                    messages.add(createMessage("user", msg.getMessage()));
                    messages.add(createMessage("assistant", msg.getMessageResponse()));
                });

        // Add current message
        messages.add(createMessage("user", request.getMessage()));

        // Prepare and send request
        ChatRequestVO chatRequestVO = ChatRequestVO.builder()
            .model(GPT_MODEL)
            .messages(messages)
            .temperature(0.7)
            .build();

        return openApiService.generateChatCompletion(chatRequestVO)
                .get("choices").get(0).get("message").get("content").asText()
                .replaceAll("```|´´´", "")
                .replace("html", "");
    }

    private void recordLogMessage(String login, String message, String response) {
        LogMessage log = new LogMessage();
        log.setLogin(login);
        log.setMessage(message);
        log.setMessageResponse(response);
        log.setDateMessage(LocalDateTime.now());
        
        logMessageRepository.save(log);
    }

    private List<MessageVO> loadPrimaryPrompts(String login) {
        List<MessageVO> messages = new ArrayList<>();
        
        promptRepository.findAll().forEach(prompt -> {
            String decodedMessage = decodeBase64(prompt.getMessage());
            String decodedResponse = decodeBase64(prompt.getMessageResponse());
            
            messages.add(createMessage("user", decodedMessage));
            messages.add(createMessage("assistant", decodedResponse));
            
            // Record in log history
            LogMessage log = new LogMessage();
            log.setLogin(login);
            log.setMessage(decodedMessage);
            log.setMessageResponse(decodedResponse);
            log.setDateMessage(LocalDateTime.now());
            logMessageRepository.save(log);
        });
        
        return messages;
    }

    private void initializeChat(String login) {
        Chat chat = new Chat();
        chat.setFlgHasPrompt(true);
        chat.setLogin(login);
        chatRepository.save(chat);
        log.info("Initialized chat for user: {}", login);
    }
    
    private MessageVO createMessage(String role, String content) {
        return MessageVO.builder()
            .role(role)
            .content(content)
            .build();
    }
    
    private String decodeBase64(String encoded) {
        return new String(Base64.getDecoder().decode(encoded.getBytes()));
    }
}
