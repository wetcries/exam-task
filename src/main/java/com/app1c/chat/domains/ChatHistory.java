package com.app1c.chat.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {
    private List<ChatMessage> history;
}
