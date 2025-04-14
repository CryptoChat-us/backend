package br.com.api.crypto_chat.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SocketConnectionHandler extends TextWebSocketHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(SocketConnectionHandler.class);
    
    private final List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());
  
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        validateSession(session);
        super.afterConnectionEstablished(session);
        logger.info("WebSocket session established: {}", session.getId());
        webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        logger.info("WebSocket session closed: {} with status: {}", session.getId(), status);
        webSocketSessions.remove(session);
    }
  
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        
        if (!(message instanceof TextMessage)) {
            logger.warn("Received non-text message from session: {}", session.getId());
            throw new IllegalArgumentException("Only text messages are supported");
        }
        
        // Send response only to the originating session
        String response = processMessage(((TextMessage) message).getPayload());
        session.sendMessage(new TextMessage(response));
    }
    
    private String processMessage(String payload) {
        // Add your message processing logic here
        return payload; // For now, just echo back
    }
    
    private void validateSession(WebSocketSession session) {
        // Check if user is authenticated
        if (session.getPrincipal() == null) {
            logger.error("Unauthorized WebSocket connection attempt");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
        }
    }
}