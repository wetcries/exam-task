package com.app1c.chat.controllers;

import com.app1c.chat.domains.ChatMessage;
import com.app1c.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public MessageController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @GetMapping
    public List<ChatMessage> getMessages() {
        return StreamSupport.stream(chatMessageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
