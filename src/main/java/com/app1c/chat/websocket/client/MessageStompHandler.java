package com.app1c.chat.websocket.client;

import com.app1c.chat.domains.ChatMessage;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MessageStompHandler extends StompSessionHandlerAdapter {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ChatMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ChatMessage message = (ChatMessage) payload;
        System.out.println(message.stringifyMessage());
    }
}
