package br.com.api.crypto_chat.feature.ChatBot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.api.crypto_chat.dto.ChatMessageRequest;
import br.com.api.crypto_chat.dto.ChatMessageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Chat Bot", description = "APIs for interacting with the chat bot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    @MessageMapping("/send")
    @SendTo("/topic/message")
    public ObjectNode cryptoChatWebsocket(ChatMessageRequest jsonMessage) throws JsonProcessingException {
        return chatBotService.processMessage(jsonMessage);
    }

    @PostMapping("/call-chat")
    public ObjectNode cryptoChat(@RequestBody ChatMessageRequest jsonMessage)
            throws JsonProcessingException {
        ObjectNode node = chatBotService.processMessage(jsonMessage);
        return node;
    }
}
