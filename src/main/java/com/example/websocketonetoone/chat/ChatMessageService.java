package com.example.websocketonetoone.chat;

import com.example.websocketonetoone.chatroom.ChatRoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSender().getId(),chatMessage.getRecipient().getId(),true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        chatRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(Long senderId,Long recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId,true);
        return chatId.map(chatRepository::findByChatId).orElse(new ArrayList<>());
    }
}
