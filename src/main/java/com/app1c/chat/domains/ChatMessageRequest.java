package com.app1c.chat.domains;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private final String sender;
    private final String message;
}
