package com.app1c.chat;

import com.app1c.chat.domains.ChatMessageRequest;
import com.app1c.chat.exceptions.ClientProblemsException;
import org.springframework.messaging.simp.stomp.StompSession;

public class ChatClient {
    StompSession stompSession;
    String username;

    public ChatClient(StompSession stompSession, String username) {
        this.stompSession = stompSession;
        this.username = username;
    }

    public void sendMessage(String text) {
        try {
            ChatMessageRequest messageRequest = new ChatMessageRequest(username, text);
            stompSession.send("/app/chat", messageRequest);
        } catch (Exception e) {
            throw new ClientProblemsException("!!! Failed to send message. Check master Client. !!!");
        }
    }
}
