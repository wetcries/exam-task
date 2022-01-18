package com.app1c.chat.repository;

import com.app1c.chat.domains.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, UUID> {
    @Query(value = "select * from chat_message order by date desc limit 50", nativeQuery = true)
    List<ChatMessage> findTop50();
}
