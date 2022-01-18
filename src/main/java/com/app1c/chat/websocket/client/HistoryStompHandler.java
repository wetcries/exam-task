package com.app1c.chat.websocket.client;

import com.app1c.chat.domains.ChatHistory;
import com.app1c.chat.domains.ChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class HistoryStompHandler extends StompSessionHandlerAdapter {
    private Logger logger = LogManager.getLogger(HistoryStompHandler.class);

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ChatHistory.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ChatHistory history = (ChatHistory) payload;
        System.out.println("Last 50 messages:");
        printHistory(history);
    }

    private void printHistory(ChatHistory history) {
        List<ChatMessage> messages = history.getHistory();
        Collections.reverse(history.getHistory());
        messages.forEach(message -> {
            System.out.println(message.stringifyMessage());
        });
    }
}
