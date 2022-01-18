package com.app1c.chat.controllers;

import com.app1c.chat.domains.ChatHistory;
import com.app1c.chat.domains.ChatMessage;
import com.app1c.chat.domains.ChatMessageRequest;
import com.app1c.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {
    private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    ChatMessage chat(ChatMessageRequest request) {

        return messageService.saveMessage(request);
    }

    @MessageMapping("/history/{sessionId}")
    @SendTo("/topic/history/{sessionId}")
    ChatHistory chatHistory(@DestinationVariable String sessionId) {
        return new ChatHistory(messageService.getLast50Messages());
    }

    private String stringifyMessage(ChatMessage message) {
        return message.getSender() + " (" + message.getDate() + ") : " + message.getMessage();
    }
}
