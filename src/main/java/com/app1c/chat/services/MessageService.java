package com.app1c.chat.services;

import com.app1c.chat.domains.ChatMessage;
import com.app1c.chat.domains.ChatMessageRequest;
import com.app1c.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public MessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage saveMessage(ChatMessageRequest request) {
        return chatMessageRepository.save(ChatMessage.builder()
                .id(UUID.randomUUID())
                .message(request.getMessage())
                .sender(request.getSender())
                .date(new Date())
                .build());
    }

    public List<ChatMessage> getMessages() {
        return StreamSupport.stream(chatMessageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<ChatMessage> getLast50Messages() {
        return chatMessageRepository.findTop50();
    }
}
