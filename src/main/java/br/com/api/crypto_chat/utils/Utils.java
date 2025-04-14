package br.com.api.crypto_chat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Utility class for creating standardized JSON responses.
 * All methods are thread-safe.
 */
public final class Utils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String STATUS_FIELD = "status";
    private static final String MESSAGE_FIELD = "message";
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";

    private Utils() {
        // Prevent instantiation
    }

    /**
     * Creates a success response with a message
     */
    public static ObjectNode createSuccessResponse(String message) {
        ObjectNode response = MAPPER.createObjectNode()
            .put(STATUS_FIELD, SUCCESS_STATUS);
        
        if (message != null && !message.isEmpty()) {
            response.put(MESSAGE_FIELD, message);
        }
        
        return response;
    }

    /**
     * Creates an error response with an optional message
     */
    public static ObjectNode createErrorResponse(String message) {
        ObjectNode response = MAPPER.createObjectNode()
            .put(STATUS_FIELD, ERROR_STATUS);
        
        if (message != null && !message.isEmpty()) {
            response.put(MESSAGE_FIELD, message);
        }
        
        return response;
    }

    /**
     * Creates a success response without a message
     */
    public static ObjectNode createSuccessResponse() {
        return createSuccessResponse(null);
    }

    /**
     * Creates an error response without a message
     */
    public static ObjectNode createErrorResponse() {
        return createErrorResponse(null);
    }

    /**
     * Creates a success response with message and additional fields
     */
    public static ObjectNode createSuccessResponseWithData(String message, String... keyValuePairs) {
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException("Key-value pairs must be provided in pairs");
        }

        ObjectNode response = createSuccessResponse(message);
        
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            response.put(keyValuePairs[i], keyValuePairs[i + 1]);
        }
        
        return response;
    }

}
