package com.example.websocketonetoone.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage,String> {
    List<ChatMessage> findByChatId(String chatId);
}
