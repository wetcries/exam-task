package com.app1c.chat.websocket.client;

import com.app1c.chat.domains.ChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
    private Logger logger = LogManager.getLogger(MyStompSessionHandler.class);

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ChatMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ChatMessage message = (ChatMessage) payload;
        System.out.println("Message handler");
        logger.info("Got message: {}", message);
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        HistoryStompHandler historyStompHandler = new HistoryStompHandler();
        MessageStompHandler messageStompHandler = new MessageStompHandler();
        session.subscribe("/topic/messages", messageStompHandler);
        session.subscribe("/topic/history/" + session.getSessionId(), historyStompHandler);
        session.send("/app/history/" + session.getSessionId(), session.getSessionId());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Exception during request: ", exception);
    }
}
