package com.app1c.chat.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue
    private UUID id;
    private String sender;
    private String message;
    private Date date;

    public String stringifyMessage() {
        return this.sender + " (" + date + ") : " + message;
    }
}
